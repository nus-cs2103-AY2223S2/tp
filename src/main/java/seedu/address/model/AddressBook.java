package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new UniqueGroupList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setGroups(newData.getGroupList());
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
     * Add the isolated event into the person's isolated event list.
     * @param person in which the event is being added to.
     * @param event that is going to be added to the person.
     */
    public void addIsolatedEvent(Person person, IsolatedEvent event) {
        person.addIsolatedEvent(event);
    }

    public void deleteIsolatedEvent(Person personToEdit, IsolatedEvent event) {
        personToEdit.getIsolatedEventList().deleteIsolatedEvent(event);
    }

    public void setIsolatedEvent(Person person, IsolatedEvent originalEvent, IsolatedEvent editedEvent) {
        requireNonNull(editedEvent);
        person.getIsolatedEventList().edit(originalEvent, editedEvent);
    }

    /**
     * Add the recurring event into the person's isolated event list
     * @param person in which the event is being added to
     * @param event that is going to be added to the person
     */
    public void addRecurringEvent(Person person, RecurringEvent event) {
        person.addRecurringEvent(event);
    }

    public void deleteRecurringEvent(Person person, RecurringEvent event) {
        person.getRecurringEventList().deleteRecurringEvent(event);
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

    /**
     * Adds {@code group} in {@code person} group set
     * {@code person} must exist in the address book.
     */
    public void addPersonInGroup(Person person, Group group) {
        person.addGroup(group);
    }

    /**
     * Removes {@code group} from {@code person} group set
     * {@code person and group} must exist in the address book.
     */
    public void removePersonFromGroup(Person person, Group group) {
        person.removeGroup(group);
    }

    /**
     * Add {@code group}  into address book's UniqueGroupList
     * {@code group} must not exist in the address book.
     */
    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Remove {@code group} from address book's UniqueGroupList and every person in the group
     * {@code group} must not exist in the address book.
     */
    public void deleteGroup(Group group) {
        Set<Person> personSet = new HashSet<>();
        for (Person p : persons) {
            if (p.getGroups().contains(group)) {
                personSet.add(p);
            }
        }
        groups.delete(group, personSet);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Returns true if a group with the same group name as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + ", " + groups.asUnmodifiableObservableList().size() + " groups";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && groups.equals(((AddressBook) other).groups));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, groups);
    }

}
