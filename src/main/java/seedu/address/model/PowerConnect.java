package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PowerConnect implements ReadOnlyPowerConnect {

    private final UniquePersonList persons;

    private final UniqueStudentList students;

    private final UniqueParentList parents;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        students = new UniqueStudentList();
        parents = new UniqueParentList();
    }

    public PowerConnect() {}

    /**
     * Creates a PowerConnect using the Persons in the {@code toBeCopied}
     */
    public PowerConnect(ReadOnlyPowerConnect toBeCopied) {
        this();
        resetParentData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    public void setParents(List<Parent> parents) {
        this.parents.setParents(parents);
    }

    /**
     * Resets the existing data of this {@code PowerConnect} with {@code newData}.
     */
    public void resetParentData(ReadOnlyPowerConnect newData) {
        requireNonNull(newData);
        setParents(newData.getParentList());
    }

    //// person-level operations

    /**
     * Returns boolean value true if a student with the same identity as {@code student} exists in PowerConnect.
     *
     * @param student Student object that needs to be checked for duplication.
     * @return Boolean value indicating whether the student already exists in PowerConnect.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a person to PowerConnect.
     * The person must not already exist in PowerConnect.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a student to PowerConnect
     * @param s of student
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    public void addParent(Parent p) {
        parents.add(p);
    }

    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    public void setParent(Parent target, Parent editedParent) {
        requireNonNull(editedParent);
        parents.setParent(target, editedParent);
    }
    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Parent> getParentList() {
        return parents.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PowerConnect // instanceof handles nulls
                && persons.equals(((PowerConnect) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
