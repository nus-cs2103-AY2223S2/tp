package codoc.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import codoc.model.person.Person;
import codoc.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the Codoc level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Codoc implements ReadOnlyCodoc {

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

    public Codoc() {}

    /**
     * Creates an Codoc using the Persons in the {@code toBeCopied}
     */
    public Codoc(ReadOnlyCodoc toBeCopied) {
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
     * Resets the existing data of this {@code Codoc} with {@code newData}.
     */
    public void resetData(ReadOnlyCodoc newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in CoDoc.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to CoDoc.
     * The person must not already exist in CoDoc.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in CoDoc.
     * The person identity of {@code editedPerson} must not be the same as another existing person in CoDoc.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Codoc}.
     * {@code key} must exist in CoDoc.
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
                || (other instanceof Codoc // instanceof handles nulls
                && persons.equals(((Codoc) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
