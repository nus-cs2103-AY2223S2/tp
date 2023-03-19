package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        events = new UniqueEventList();
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

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
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
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
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

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }


    //// event-level operations

    /**
     * Returns true if an event with the same date time information as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Removes {@code eventToDelete} from this {@code AddressBook}.
     * {@code eventToDelete} must exist in the address book.
     */
    public void deleteEventFromPersonList(Event eventToDelete) {
        requireNonNull(eventToDelete);
        for (Person personToCheck: getPersonList()) {
            if (personToCheck.getEventSet().contains(eventToDelete)) {
                // Person needs to have the event tag deleted
                Set<Event> editedEventSet = new HashSet<>();
                for (Event eventToCheck : personToCheck.getEventSet()) {
                    if (!eventToCheck.equals(eventToDelete)) {
                        // Event tags that are not deleted will not be removed
                        editedEventSet.add(eventToCheck);
                    }
                }
                Person editedPerson = new Person(personToCheck.getName(), personToCheck.getPhone(),
                        personToCheck.getEmail(), personToCheck.getAddress(), editedEventSet);
                persons.setPerson(personToCheck, editedPerson);
            }
        }
    }

    /**
     * Replaces the given event {@code target} in the event set of all persons in address book with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    public void setEventFromPersonList(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        for (Person personToCheck: getPersonList()) {
            if (personToCheck.getEventSet().contains(target)) {
                // Person needs to have the event tag replaced
                Set<Event> editedEventSet = new HashSet<>();
                for (Event eventToCheck : personToCheck.getEventSet()) {
                    if (!eventToCheck.equals(target)) {
                        // Event tags that are not edited will not be removed
                        editedEventSet.add(eventToCheck);
                    } else {
                        // Event tag that is being edited will be replaced
                        editedEventSet.add(editedEvent);
                    }
                }
                Person editedPerson = new Person(personToCheck.getName(), personToCheck.getPhone(),
                        personToCheck.getEmail(), personToCheck.getAddress(), editedEventSet);
                persons.setPerson(personToCheck, editedPerson);
            }
        }
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + events.asUnmodifiableObservableList().size() + " events";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
        //TODO: compare events here
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
