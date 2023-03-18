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
import seedu.address.model.person.PcClass;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.person.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Parents parents;
    private final PcClass pcClass;
    private final UserPrefs userPrefs;

    private final FilteredList<Student> filteredStudents;

    private final FilteredList<Parent> filteredParents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
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
     * Returns the user prefs' parent file path.
     */
    @Override
    public Path getParentFilePath() {
        return userPrefs.getParentsFilePath();
    }

    /**
     * Sets the user prefs' address book file path.
     *
     * @param pcClassFilePath
     */
    @Override
    public void setPcClassFilePath(Path pcClassFilePath) {
        requireNonNull(pcClassFilePath);
        userPrefs.setPcClassFilePath(pcClassFilePath);
    }

    /**
     * Sets the user prefs' parent file path.
     *
     * @param parentFilePath
     */
    @Override
    public void setParentFilePath(Path parentFilePath) {
        requireNonNull(parentFilePath);
        userPrefs.setParentsFilePath(parentFilePath);
    }

    /**
     * Replaces pcclass data with the data in {@code pcclass}.
     *
     * @param readOnlyPcClass
     */
    @Override
    public void setPcClass(ReadOnlyPcClass readOnlyPcClass) {
        this.pcClass.resetData(readOnlyPcClass);
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
        System.out.println(target.getStudentClass().getClassName());
        Class c = Class.of(target.getStudentClass().getClassName());

        c.setStudent(target, editedStudent);
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
