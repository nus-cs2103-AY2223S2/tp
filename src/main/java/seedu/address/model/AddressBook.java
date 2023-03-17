package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEventList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueEventList();
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
     * Replaces the contents of the event list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Event> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// event-level operations

    /**
     * Returns true if a event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasPerson(Event person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a event to the address book.
     * The event must not already exist in the address book.
     */
    public void addPerson(Event p) {
        persons.add(p);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedPerson} must not be the same as another existing event in the address book.
     */
    public void setPerson(Event target, Event editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Event key) {
        persons.remove(key);
    }

    /**
     * Marks {@code target} from this {@code AddressBook}.
     * {@code target} must exist in the address book.
     */
    public void markEvent(Event target) {
        persons.mark(target);
    }

    /**
     * Unmarks {@code target} from this {@code AddressBook}.
     * {@code target} must exist in the address book.
     */
    public void unmarkEvent(Event target) {
        persons.unmark(target);
    }

    public void linkContact(Event event, Event linkedEvent) {
        persons.linkContact(event, linkedEvent);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Event> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
