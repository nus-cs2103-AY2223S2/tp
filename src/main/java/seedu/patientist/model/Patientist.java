package seedu.patientist.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;

/**
 * Wraps all data at the patientist-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Patientist implements ReadOnlyPatientist {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public Patientist() {}

    /**
     * Creates an Patientist using the Persons in the {@code toBeCopied}
     */
    public Patientist(ReadOnlyPatientist toBeCopied) {
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

    /**
     * Resets the existing data of this {@code Patientist} with {@code newData}.
     */
    public void resetData(ReadOnlyPatientist newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the patientist book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the patientist book.
     * The person must not already exist in the patientist book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the patientist book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the patientist.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Patientist}.
     * {@code key} must exist in the patientist book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Patientist // instanceof handles nulls
                && persons.equals(((Patientist) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
