package taa.model;

import static java.util.Objects.requireNonNull;

import java.awt.Color;
import java.awt.Font;
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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import taa.commons.core.GuiSettings;
import taa.commons.core.LogsCenter;
import taa.commons.core.index.Index;
import taa.commons.util.CollectionUtil;
import taa.logic.commands.enums.ChartType;
import taa.model.alarm.Alarm;
import taa.model.alarm.AlarmList;
import taa.model.assignment.Assignment;
import taa.model.assignment.AssignmentList;
import taa.model.assignment.exceptions.AssignmentException;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.DuplicateAssignmentException;
import taa.model.assignment.exceptions.NoGradeVarianceException;
import taa.model.assignment.exceptions.NoSubmissionsFoundException;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.SameStudentPredicate;
import taa.model.student.Student;
import taa.model.tag.Tag;
import taa.storage.TaaData;

/**
 * Represents the in-memory model of the student listdata.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClassList classList;
    private final UserPrefs userPrefs;
    private final Tutor tutor;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<ClassList> filteredClassLists;
    private final AlarmList alarmList;

    private final AssignmentList assignmentList = AssignmentList.INSTANCE;
    private Predicate<ClassList> activeClassListPredicate;


    /**
     * Initializes a ModelManager with the given classList and userPrefs.
     */
    public ModelManager(TaaData taaData, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(taaData, taaData.studentList, userPrefs);

        logger.fine("Initializing with student list: " + taaData.studentList + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.classList = new ClassList(taaData.studentList);
        UniqueClassLists temp = new UniqueClassLists(this.classList);
        this.tutor = new Tutor(new Name("James"), new HashSet<>(), temp);
        this.filteredStudents = new FilteredList<>(this.classList.getStudentList());
        this.filteredClassLists = new FilteredList<>(this.tutor.getClassList());
        this.activeClassListPredicate = null;
        this.alarmList = new AlarmList();

        for (Student student : this.classList.getUniqueStudentList()) {
            addStudentToTaggedClasses(student);
        }

        initAssignmentsFromStorage(taaData.asgnArr);
    }

    public ModelManager() {
        this(new TaaData(), new UserPrefs());
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public Path getTaaDataFilePath() {
        return userPrefs.getTaaDataFilePath();
    }

    @Override
    public void setTaaDataFilePath(Path taaDataFilePath) {
        requireNonNull(taaDataFilePath);
        userPrefs.setTaaDataFilePath(taaDataFilePath);
    }

    @Override
    public TaaData getTaaData() {
        return new TaaData(classList, assignmentList.getAssignments());
    }

    @Override
    public void setTaaData(TaaData taaData) {
        this.classList.resetData(taaData.studentList);
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return classList.hasStudent(student);
    }

    /**
     * Check whether the tutor already has the class.
     *
     * @param classList the class name to be checked.
     * @return Boolean variable indicating whether it's contained.
     */
    public boolean hasClassList(ClassList classList) {
        requireNonNull(classList);
        return tutor.containsClassList(classList);
    }

    @Override
    public int getClassListSize() {
        return this.filteredStudents.size();
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


    //=========== ClassList ================================================================================

    @Override
    public void addClassList(ClassList toAdd) {
        tutor.addClass(toAdd);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
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

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of {@code
     * versionedAddressBook}
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
            filteredStudents.setPredicate(p -> false);
        }
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

    @Override
    public boolean hasAssignment(String assignmentName) {
        requireNonNull(assignmentName);
        return assignmentList.contains(assignmentName);
    }

    //=========== AssignmentList Helpers =============================================================

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
        Student student;
        try {
            student = this.filteredStudents.get(Index.fromOneBased(studentId).getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new AssignmentException(String.format("Invalid student id: %d", studentId));
        }
        assignmentList.grade(assignmentName, student, marks, isLateSubmission);
        updateStudent(student);
    }

    @Override
    public String listAssignments() {
        return assignmentList.list();
    }

    @Override
    public void ungrade(String assignmentName, int studentId) throws AssignmentException {
        Student student;
        try {
            student = this.filteredStudents.get(Index.fromOneBased(studentId).getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new AssignmentException(String.format("Invalid student id: %d", studentId));
        }
        assignmentList.ungrade(assignmentName, student);
        updateStudent(student);
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
            String alertString = AlarmList.getAlarmAlert(alarm);
            AlarmList.deleteTheAlarm(alarm); //when the alarm is sounded, it's deleted from the alarm list
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alarm Warning");
            alert.setHeaderText("Hey, your time's up!");
            alert.setContentText("Note: " + alertString);
            alert.show();

        }));
        timeline.setCycleCount(1);
        timeline.play();
        alarm.addTimeline(timeline);
        this.alarmList.addAlarm(alarm);
    }

    @Override
    public void deleteStudentSubmission(Student studentToDelete) {
        assignmentList.deleteStudent(studentToDelete);
    }

    @Override
    public void initAssignmentsFromStorage(Assignment[] asgnArr) {
        assignmentList.initFromStorage(filteredStudents, asgnArr);
        for (Student stu : filteredStudents) {
            updateStudent(stu);
        }
    }

    /**
     * @param stu student to be added to assignmentList
     */
    public void addStudentAssignment(Student stu) {
        assignmentList.addStudent(stu);
        updateStudent(stu);
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
            logger.info("Generating grade distribution for assignment " + args[0] + "...");
            chart = generateGradeDistribution(args[0]);
            break;
        case CLASS_ATTENDANCE:
            title = "Attendance Distribution";
            logger.info("Generating attendance distribution..");
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

    //Solution below adapted from ChatGPT
    @Override
    public String listAlarms() {
        if (AlarmList.getAlarmCount() == 0) {
            return "There is no alarm as of now.";
        }
        return this.alarmList.list();
    }

    @Override
    public void deleteAlarm(int index) {
        AlarmList.deleteTheAlarm(index);
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

    private JFreeChart generateGradeDistribution(String assignmentName)
            throws AssignmentNotFoundException, NoSubmissionsFoundException, NoGradeVarianceException {
        requireNonNull(assignmentName);

        final int numStdToShow = 4;
        final int numPointsToSample = 300;

        IntStatistics statistics = getGradeStatistics(assignmentName);
        double mean = statistics.getMean();
        double std = statistics.getStdDev();

        logger.info(String.format("Attempting to generate grade distribution for assignment %s"
                + " with a mean of %f and a standard deviation of %f.", assignmentName, mean, std));

        if (std == 0) {
            // No variance, don't need bell curve
            throw new NoGradeVarianceException(assignmentName, mean);
        }

        Function2D bellCurve = new NormalDistributionFunction2D(mean, std);
        XYDataset dataset = DatasetUtils.sampleFunction2D(
                bellCurve,
                mean - numStdToShow * std,
                mean + numStdToShow * std,
                numPointsToSample,
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

        double maxY = result.getXYPlot().getRangeAxis().getRange().getUpperBound();
        final Marker meanInfo = new ValueMarker(maxY);
        meanInfo.setLabel(String.format("Mean: %.2f", mean));
        meanInfo.setLabelPaint(Color.blue);
        meanInfo.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
        meanInfo.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        meanInfo.setLabelFont(new Font("Arial", 0, 24));
        result.getXYPlot().addRangeMarker(meanInfo);

        final Marker stdInfo = new ValueMarker(0.95 * maxY);
        stdInfo.setLabel(String.format("Std. Dev: %.4f", std));
        stdInfo.setLabelPaint(Color.blue);
        stdInfo.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
        stdInfo.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        stdInfo.setLabelFont(new Font("Arial", 0, 24));
        result.getXYPlot().addRangeMarker(stdInfo);

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
                    String.format("W%d", i + 1));
        }

        result = ChartFactory.createBarChart(
                "Attendance",
                "Week",
                "Number of Students",
                attendanceData,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
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
