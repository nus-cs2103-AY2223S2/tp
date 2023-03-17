package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.UniqueParentList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
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
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setParents(newData.getParentList());
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

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
     * Returns boolean value true if a parent with the same identity as {@code parent} exists in PowerConnect.
     *
     * @param parent Parent object that needs to be checked for duplication.
     * @return Boolean value indicating whether the parent already exists in PowerConnect.
     */
    public boolean hasParent(Parent parent) {
        requireNonNull(parent);
        return parents.contains(parent);
    }

    /**
     * Returns boolean value true if Phone is currently not attached to any Parent or if Phone and Name matches an
     * existing Parent.
     *
     * @param phone Phone object to be checked with.
     * @param parentName Name object to be checked with.
     * @return Boolean value result from the checks.
     */
    public boolean canInitialize(Phone phone, Name parentName) {
        requireNonNull(phone);
        requireNonNull(parentName);
        for (Parent p : parents) {
            if (phone.equals(p.getPhone())) {
                return p.getName().equals(parentName);
            }
        }
        return true;
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a student to addressBook/PowerConnect
     * @param s of student
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    public void addParent(Parent p) {
        parents.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    public void setParent(Parent target, Parent editedParent) {
        requireNonNull(editedParent);
        parents.setParent(target, editedParent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes a student
     * @param key
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    public void removeParent(Parent key) {
        parents.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {

        return persons.asUnmodifiableObservableList();
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
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
