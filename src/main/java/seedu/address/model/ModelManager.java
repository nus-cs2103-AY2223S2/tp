package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Class;
import seedu.address.model.person.Name;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;

/**
 * Represents the in-memory model of the PowerConnect data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private Parents parents;
    private final PcClass pcClass;
    private final UserPrefs userPrefs;

    private FilteredList<Student> filteredStudents;

    private FilteredList<Parent> filteredParents;

    /**
     * Initializes a ModelManager with the given PowerConnect and userPrefs.
     */
    public ModelManager(ReadOnlyPcClass readOnlyPcClass, ReadOnlyParents readOnlyParents, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(readOnlyParents, readOnlyPcClass, userPrefs);

        logger.fine("Initializing with classes: " + readOnlyPcClass
                + " and parents: " + readOnlyParents
                + " and user prefs " + userPrefs);


        this.pcClass = new PcClass(readOnlyPcClass);
        this.parents = new Parents(readOnlyParents);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(Class.getAllStudents().asUnmodifiableObservableList());
        filteredParents = new FilteredList<>(this.parents.getParentList());
    }

    /**
     * Initializes a ModelManager with the given PowerConnect and userPrefs.
     */
    public ModelManager(ReadOnlyPcClass readOnlyPcClass, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(readOnlyPcClass, userPrefs);
        logger.fine("Initializing with classes: " + readOnlyPcClass
                + " and user prefs " + userPrefs);
        this.pcClass = new PcClass(readOnlyPcClass);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(Class.getAllStudents().asUnmodifiableObservableList());
    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyParents readOnlyParents, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(readOnlyParents, userPrefs);

        logger.fine("parents: " + readOnlyParents
                + " and user prefs " + userPrefs);

        this.pcClass = null;
        this.parents = new Parents(readOnlyParents);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredParents = new FilteredList<>(this.parents.getParentList());
    }

    public ModelManager() {
        this(new PcClass(), new Parents(), new UserPrefs());
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

    /**
     * Returns the user prefs' pcclass file path.
     */
    @Override
    public Path getPcClassFilePath() {
        return userPrefs.getPcClassFilePath();
    }

    /**
     * Replaces parent data with the data in {@code parent}.
     *
     * @param readOnlyParents
     */
    @Override
    public void setParents(ReadOnlyParents readOnlyParents) {
        this.parents.resetData(readOnlyParents);
    }


    /**
     * Returns the PCClass
     */
    @Override
    public ReadOnlyPcClass getPcClass() {
        return pcClass;
    }

    /**
     * Returns the Parents
     */
    @Override
    public ReadOnlyParents getParents() {
        return parents;
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the PCClass.
     *
     * @param student must not be null.
     * @return true if a student with the same identity as {@code student} exists in the PCClass.
     */
    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return Class.getAllStudents().contains(student);
    }

    /**
     * Returns student with the same index number and Class as {@code student} exists in the PCClass.
     *
     * @param indexNumber must not be null.
     * @param studentClass must not be null.
     * @return stuent with the same index number and Class as {@code student} exists in the PCClass.
     */
    @Override
    public Student getStudent(IndexNumber indexNumber, Class studentClass) {
        requireAllNonNull(indexNumber, studentClass);
        UniqueStudentList students = Class.getAllStudents();

        for (Student student : students) {
            IndexNumber currStudentIndexNumber = student.getIndexNumber();
            Class currStudentClass = student.getStudentClass();

            if (currStudentIndexNumber.equals(indexNumber) && currStudentClass.equals(studentClass)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in the address book.
     *
     * @param parent must not be null.
     * @return true if a parent with the same identity as {@code parent} exists in the address book.
     */
    @Override
    public boolean hasParent(Parent parent) {
        requireNonNull(parent);
        return parents.getParentList().contains(parent);
    }

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in the PowerConnect.
     *
     * @param name must not be null.
     * @param phone must not be null.
     * @return true if a parent with the same identity as {@code parent} exists in the PowerConnect.
     */
    @Override
    public Parent getParent(Name name, Phone phone) {
        requireAllNonNull(name, phone);
        ObservableList<Parent> parentList = parents.getParentList();
        for (Parent parent : parentList) {
            Name currParentName = parent.getName();
            Phone currParentPhoneNumber = parent.getPhone();

            if (currParentName.equals(name) && currParentPhoneNumber.equals(phone)) {
                return parent;
            }
        }
        return null;
    }

    /**
     * Deletes the given student.
     * The person must exist in the address book.
     *
     * @param target
     */
    @Override
    public void deleteStudent(Student target) {
        for (Class c : pcClass.getClassList()) {
            if (c.isSameClass(target.getStudentClass())) {
                c.removeStudent(target);
            }
        }
    }

    /**
     * Deletes the given parent.
     *
     * @param parent must exist in the address book.
     */
    @Override
    public void deleteParent(Parent parent) {
        parents.removeParent(parent);
    }

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     *
     * @param student
     */
    @Override
    public void addStudent(Student student, Class c) {
        c.addStudent(student);
        pcClass.addClass(c);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    /**
     * Adds the given parent.
     *
     * @param parent must not already exist in the address book.
     */
    @Override
    public void addParent(Parent parent) {
        parents.addParent(parent);
    }

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the PCClass.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the pcclass.
     *
     * @param target        must exist in the address book.
     * @param editedStudent must not be the same as another existing student in the address book.
     */
    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        Class cTarget = Class.of(target.getStudentClass().getClassName());
        cTarget.removeStudent(target);
        Class cEdit = Class.of(editedStudent.getStudentClass().getClassName());
        cEdit.addStudent(editedStudent);
    }



    /**
     * Replaces the given parent {@code target} with {@code editedParent}.
     * @param target must exist in the address book.
     * @param editedParent must not be the same as another existing parent in the address book.
     */
    @Override
    public void setParent(Parent target, Parent editedParent) {
        requireAllNonNull(target, editedParent);
        parents.setParent(target, editedParent);
    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Parent> getFilteredParentList() {
        return filteredParents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents = new FilteredList<>(Class.getAllStudents().asUnmodifiableObservableList());
        filteredStudents.setPredicate(predicate);
    }
    @Override
    public void updateFilteredStudentListFind(Predicate<Student> predicate, Class sc) {
        requireNonNull(predicate);
        filteredStudents = new FilteredList<>(sc.getStudents().asUnmodifiableObservableList());
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredParentList(Predicate<Parent> predicate) {
        requireNonNull(predicate);
        filteredParents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelManager that = (ModelManager) o;
        return Objects.equals(parents, that.parents)
                && Objects.equals(pcClass, that.pcClass)
                && Objects.equals(userPrefs, that.userPrefs)
                && Objects.equals(filteredStudents, that.filteredStudents)
                && Objects.equals(filteredParents, that.filteredParents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parents, pcClass, userPrefs, filteredStudents, filteredParents);
    }
}
