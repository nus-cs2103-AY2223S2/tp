package seedu.socket.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.UniquePersonList;

/**
 * Wraps all data at the SOCket level.
 * Duplicates are not allowed (by {@link Person#isSamePerson} comparison)
 */
public class Socket implements ReadOnlySocket {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public Socket() {}

    /**
     * Creates a {@code Socket} using the Persons in the {@code toBeCopied}
     */
    public Socket(ReadOnlySocket toBeCopied) {
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
     * Resets the existing data of this {@code Socket} with {@code newData}.
     */
    public void resetData(ReadOnlySocket newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in {@code Socket}.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to {@code Socket}.
     * The person must not already exist in {@code Socket}.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in {@code Socket}.
     * The person identity of {@code editedPerson} must not be the same as another existing person in {@code Socket}.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Socket}.
     * {@code key} must exist in {@code Socket}.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes all persons by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public boolean removeAllPerson(Predicate<Person> predicate) {
        return persons.removeAll(predicate);
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
                || (other instanceof Socket // instanceof handles nulls
                && persons.equals(((Socket) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    public void sort(String category) {
        persons.sort(category);
    }
}
