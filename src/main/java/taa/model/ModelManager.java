package taa.model;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Duration;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;
import taa.assignment.AssignmentList;
import taa.assignment.exceptions.*;
import taa.commons.core.GuiSettings;
import taa.commons.core.LogsCenter;
import taa.commons.core.index.Index;
import taa.commons.util.CollectionUtil;
import taa.logic.commands.enums.ChartType;
import taa.logic.commands.exceptions.CommandException;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.SameStudentPredicate;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClassList classList;
    private final UserPrefs userPrefs;
    private final Tutor tutor;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<ClassList> filteredClassLists;

    private final AssignmentList assignmentList = new AssignmentList();
    private Predicate<ClassList> activeClassListPredicate;

    /**
     * Initializes a ModelManager with the given classList and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.classList = new ClassList(addressBook);
        UniqueClassLists temp = new UniqueClassLists(this.classList);
        this.tutor = new Tutor(new Name("James"), new HashSet<>(), temp);
        this.filteredStudents = new FilteredList<>(this.classList.getStudentList());
        this.filteredClassLists = new FilteredList<ClassList>(this.tutor.getClassList());
        this.activeClassListPredicate = null;

        for (Student student : this.classList.getUniqueStudentList()) {
            addStudentToTaggedClasses(student);
        }

    }

    public ModelManager() {
        this(new ClassList(), new UserPrefs());
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.classList.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return classList;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return classList.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        classList.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        classList.addStudent(student);
        addStudentToTaggedClasses(student);
        if (activeClassListPredicate == null) {
            updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        } else {
            updateFilteredClassLists(activeClassListPredicate);
        }
    }

    @Override
    public void updateStudent(Student target) {
        classList.updateStudent(target);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        CollectionUtil.requireAllNonNull(target, editedStudent);

        classList.setStudent(target, editedStudent);
    }


    //=========== ClassList ================================================================================

    /**
     * Check whether the tutor already has the class.
     * @param classList the class name to be checked.
     * @return Boolean variable indicating whether it's contained.
     */
    public boolean hasClassList(ClassList classList) {
        requireNonNull(classList);
        return tutor.containsClassList(classList);
    }

    @Override
    public void addClassList(ClassList toAdd) {
        tutor.addClass(toAdd);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void addStudentToTaggedClasses(Student student) {
        requireNonNull(student);

        Set<Tag> classTags = student.getClassTags();
        for (Tag tag : classTags) {
            String className = tag.tagName;
            this.tutor.addStudentToClass(student, className);
        }
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Student> getFilteredClassList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        this.activeClassListPredicate = null;
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredClassLists(Predicate<ClassList> predicate) {
        requireNonNull(predicate);
        //filteredClassLists.setPredicate(predicate);
        this.activeClassListPredicate = predicate;
        FilteredList<ClassList> filtered = filteredClassLists.filtered(this.activeClassListPredicate);
        if (filtered.size() > 0) {
            filteredStudents.setPredicate(new SameStudentPredicate(filtered.get(0)));
        } else {
            filteredStudents.setPredicate(p->false);
        }
    }

    @Override
    public int getClassListSize() {
        return this.filteredStudents.size();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return classList.equals(other.classList)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== AssignmentList Helpers =============================================================

    @Override
    public boolean hasAssignment(String assignmentName) {
        requireNonNull(assignmentName);
        return assignmentList.contains(assignmentName);
    }

    @Override
    public void addAssignment(String assignmentName, int totalMarks) throws DuplicateAssignmentException {
        assignmentList.add(assignmentName, filteredStudents, totalMarks);
        for (Student student : filteredStudents) {
            updateStudent(student);
        }
    }

    @Override
    public void deleteAssignment(String assignmentName) throws AssignmentNotFoundException {
        assignmentList.delete(assignmentName);
        for (Student student : filteredStudents) {
            updateStudent(student);
        }
    }

    @Override
    public void grade(String assignmentName, int studentId, int marks, boolean isLateSubmission)
            throws AssignmentException {
        Student student = this.filteredStudents.get(Index.fromOneBased(studentId).getZeroBased());
        assignmentList.grade(assignmentName, student, marks, isLateSubmission);
        updateStudent(student);
    }

    @Override
    public void ungrade(String assignmentName, int studentId) throws AssignmentException {
        Student student = this.filteredStudents.get(Index.fromOneBased(studentId).getZeroBased());
        assignmentList.ungrade(assignmentName, student);
        updateStudent(student);
    }

    @Override
    public String listAssignments() {
        return assignmentList.list();
    }

    //Solution below adapted from ChatGPT
    @Override
    public void addAlarm(Alarm alarm) {
        Duration duration = Duration.minutes(alarm.getTime());
        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
            String music = "src/main/resources/sounds/bell.wav";
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(music));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
        timeline.play();
    }

    @Override
    public void displayChart(ChartType chartType, String... args)
            throws AssignmentNotFoundException, NoSubmissionsFoundException, NoGradeVarianceException {
        String title;
        JFreeChart chart;

        switch (chartType) {
        case CLASS_GRADES:
            // args[0] should not be null here -- should be ensured in ClassStatisticsCommandParser
            assert args[0] != null;

            title = "Grade Distribution";
            chart = generateGradeDistribution(args[0]);
            break;
        case CLASS_ATTENDANCE:
            title = "Attendance Distribution";
            chart = generateAttendanceDistribution();
            break;
        default:
            // this should be unreachable; any invalid ChartType should
            // already be handled by ClassStatisticsCommandParser
            assert false;
            return;
        }

        ChartFrame frame = new ChartFrame(title, chart);
        frame.pack();
        frame.setVisible(true);
    }

    private JFreeChart generateGradeDistribution(String assignmentName)
            throws AssignmentNotFoundException, NoSubmissionsFoundException, NoGradeVarianceException {
        requireNonNull(assignmentName);

        final int NUM_STD_TO_SHOW = 4;
        final int NUM_POINTS_TO_SAMPLE = 300;

        IntStatistics statistics = getGradeStatistics(assignmentName);
        double mean = statistics.getMean();
        double std = statistics.getStdDev();

        if (std == 0) {
            // No variance, don't need bell curve
            throw new NoGradeVarianceException(assignmentName, mean);
        }

        Function2D bellCurve = new NormalDistributionFunction2D(mean, std);
        XYDataset dataset = DatasetUtils.sampleFunction2D(
                bellCurve,
                mean - NUM_STD_TO_SHOW * std,
                mean + NUM_STD_TO_SHOW * std,
                NUM_POINTS_TO_SAMPLE,
                "Normal"
        );

        JFreeChart result = ChartFactory.createXYLineChart(
                "Grade Distribution for Assignment " + assignmentName,
                "Score",
                "Probability",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        double max_Y = result.getXYPlot().getRangeAxis().getRange().getUpperBound();
        final Marker mean_info = new ValueMarker(max_Y);
        mean_info.setLabel(String.format("Mean: %.2f", mean));
        mean_info.setLabelPaint(Color.blue);
        mean_info.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
        mean_info.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        mean_info.setLabelFont(new Font("Arial", 0, 24));
        result.getXYPlot().addRangeMarker(mean_info);

        final Marker std_info = new ValueMarker(0.95 * max_Y);
        std_info.setLabel(String.format("Std. Dev: %.4f", std));
        std_info.setLabelPaint(Color.blue);
        std_info.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
        std_info.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        std_info.setLabelFont(new Font("Arial", 0, 24));
        result.getXYPlot().addRangeMarker(std_info);

        return result;
    }

    private JFreeChart generateAttendanceDistribution() {
        JFreeChart result;
        DefaultCategoryDataset attendanceData = new DefaultCategoryDataset();
        int[] studentAttendance = countStudentAttendance();

        for (int i = 0; i < studentAttendance.length; i++) {
            attendanceData.setValue(
                    studentAttendance[i],
                    "Present",
                    String.format("W%d", i+1));
        }

        result = ChartFactory.createBarChart(
                "Attendance",     //Chart title
                "Week",     //Domain axis label
                "Number of Students",         //Range axis label
                attendanceData,         //Chart Data
                PlotOrientation.VERTICAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false             // include URLs?
        );
        ValueAxis axis = result.getCategoryPlot().getRangeAxis();
        axis.setRange(0, filteredStudents.size());
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return result;
    }

    private int[] countStudentAttendance() {
        int[] result = new int[Attendance.NUM_WEEKS];

        for (Student student : filteredStudents) {
            student.updateAttendanceCounter(result);
        }

        return result;
    }

    private IntStatistics getGradeStatistics(String assignmentName)
            throws AssignmentNotFoundException, NoSubmissionsFoundException {
        requireNonNull(assignmentName);

        List<Integer> grades = new ArrayList<>();

        for (Student student : filteredStudents) {
            student.getGradesForAssignment(assignmentName).ifPresent(grades::add);
        }

        if (grades.isEmpty()) {
            throw new NoSubmissionsFoundException(assignmentName);
        }

        return new IntStatistics(grades);
    }
}
