package tfifteenfour.clipboard.model;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Roster roster;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Student> viewedStudent;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyRoster addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.roster = new Roster(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.roster.getStudentList());
        viewedStudent = new FilteredList<>(this.roster.getStudentList());
    }

    public ModelManager() {
        this(new Roster(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

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
    public Path getRosterFilePath() {
        return userPrefs.getRosterFilePath();
    }

    @Override
    public void setRosterFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setRosterFilePath(addressBookFilePath);
    }

    //=========== Roster ================================================================================

    @Override
    public void setRoster(ReadOnlyRoster addressBook) {
        this.roster.resetData(addressBook);
    }

    @Override
    public ReadOnlyRoster getRoster() {
        return roster;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return roster.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        roster.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        roster.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        roster.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedRoster}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
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
        return roster.equals(other.roster)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== Viewed Student Accessors =============================================================
    @Override
    public Student getViewedStudent() {
        return viewedStudent.get(0);
    }

    @Override
    public void updateViewedStudent(Predicate<Student> predicate) {
        requireNonNull(predicate);
        viewedStudent.setPredicate(predicate);
    }

}
