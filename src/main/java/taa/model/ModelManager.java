package taa.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import taa.assignment.AssignmentList;
import taa.commons.core.GuiSettings;
import taa.commons.core.LogsCenter;
import taa.commons.util.CollectionUtil;
import taa.logic.commands.exceptions.CommandException;
import taa.model.student.Name;
import taa.model.student.SameStudentPredicate;
import taa.model.student.Student;

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

    /**
     * Initializes a ModelManager with the given classList and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.classList = new ClassList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        UniqueClassLists temp = new UniqueClassLists(this.classList);
        this.tutor = new Tutor(new Name("James"), new HashSet<>(), temp);
        filteredStudents = new FilteredList<>(this.classList.getStudentList());
        filteredClassLists = new FilteredList<ClassList>(this.tutor.getClassList());
    }

    public ModelManager() {
        this(new ClassList(), new UserPrefs());
    }

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

    //=========== ClassList ================================================================================

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
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

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
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredClassLists(Predicate<ClassList> predicate) {
        requireNonNull(predicate);
        //filteredClassLists.setPredicate(predicate);
        FilteredList<ClassList> filtered = filteredClassLists.filtered(predicate);
        if (filtered.size() > 0) {
            filteredStudents.setPredicate(new SameStudentPredicate(filtered.get(0)));
        } else {
            filteredStudents.setPredicate(p->false);
        }
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
    public void addAssignment(String assignmentName) throws CommandException {
        assignmentList.add(assignmentName, filteredStudents);
    }

    @Override
    public void deleteAssignment(String assignmentName) throws CommandException {
        assignmentList.delete(assignmentName);
    }

    @Override
    public void grade(String assignmentName, int studentId, int marks) throws CommandException {
        assignmentList.grade(assignmentName, studentId, marks);
    }
}
