package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Class;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Parent> PREDICATE_SHOW_ALL_PARENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' pcclass file path.
     */
    Path getPcClassFilePath();

    /**
     * Replaces parent data with the data in {@code parent}.
     */
    void setParents(ReadOnlyParents readOnlyParents);

    /** Returns the PCClass */
    ReadOnlyPcClass getPcClass();

    /** Returns the Parents */
    ReadOnlyParents getParents();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the PCClass.
     * @param student must not be null.
     * @return true if a student with the same identity as {@code student} exists in the PCClass.
     */
    boolean hasStudent(Student student);

    /**
     * Returns student with the same index number and class as {@code student} exists in the PCClass.
     * @param indexNumber must not be null.
     * @param studentClass must not be null.
     * @return student with the same index number and class as {@code student} exists in the PCClass.
     */
    Student getStudent(IndexNumber indexNumber, Class studentClass);

    /**
     * Returns true if a parent with the same identity as {@code parent} exists in the PowerConnect.
     * @param parent must not be null.
     * @return true if a parent with the same identity as {@code parent} exists in the PowerConnect.
     */
    boolean hasParent(Parent parent);

    /**
     * Returns true if a parent with the same name and phone number as {@code parent} exists in the PowerConnect.
     * @param name must not be null.
     * @param phone must not be null.
     * @return true if a parent with the same name and phone number as {@code parent} exists in the PowerConnect.
     */
    Parent getParent(Name name, Phone phone);

    /**
     * Deletes the given student.
     * The person must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Deletes the given parent.
     * @param parent must exist in the address book.
     */
    void deleteParent(Parent parent);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student, Class c);

    /**
     * Adds the given parent.
     * @param parent must not already exist in the address book.
     */
    void addParent(Parent parent);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the PCClass.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the pcclass.
     * @param target must exist in the address book.
     * @param editedStudent must not be the same as another existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Replaces the given parent {@code target} with {@code editedParent}.
     * {@code target} must exist in the address book.
     * The parent identity of {@code editedParent} must not be the same as another existing parent in the address book.
     * @param target must exist in the address book.
     * @param editedParent must not be the same as another existing parent in the address book.
     */
    void setParent(Parent target, Parent editedParent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();
    /** Returns an unmodifiable view of the filtered parent list */
    ObservableList<Parent> getFilteredParentList();
    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @param predicate must not be null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered parent list to filter by the given {@code predicate}.
     * @param predicate must not be null.
     */
    void updateFilteredParentList(Predicate<Parent> predicate);

    void updateFilteredStudentListFind(Predicate<Student> predicate, Class sc);
}
