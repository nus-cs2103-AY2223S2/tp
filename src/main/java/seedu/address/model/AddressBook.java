package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.MeetingWithPerson;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

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

    //// meeting operations

    /**
     * Adds a meeting to the Person
     * @param personToEdit person we want to add meeting to
     * @param meeting meeting that is to be added
     * @return Person object with a new meeting added
     */
    public Person addMeeting(Person personToEdit, Meeting meeting) {
        personToEdit.getMeetings().add(meeting);
        Person editedPerson = new Person(
            personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getMeetings());
        this.setPerson(personToEdit, editedPerson);
        persons.refreshInternalMeetingList();
        return editedPerson;
    }

    /**
     * Removes a meeting from the {@code Person} list of meetings
     * @param personToEdit person we want to remove the meeting from
     * @param indexMeeting index in meeting list at which the meeting is to be removed
     * @return Person with specified meeting removed
     */
    public Person removeMeeting(Person personToEdit, Index indexMeeting) {
        personToEdit.getMeetings().remove(indexMeeting.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getMeetings());
        this.setPerson(personToEdit, editedPerson);
        persons.refreshInternalMeetingList();
        return editedPerson;
    }

    /**
     * Update {@code Person} list of meetings with updated meeting information
     * @param personToEdit person we want to update meeting to
     * @param meetingIndex index in meeting list in which we want to update the meeting
     * @param editedMeeting the updated meeting
     */
    public void updateMeeting(Person personToEdit, Index meetingIndex, Meeting editedMeeting) {
        personToEdit.setMeeting(meetingIndex.getZeroBased(), editedMeeting);
        persons.refreshInternalMeetingList();
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
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    @Override
    public ObservableList<MeetingWithPerson> getMeetingList() {
        return persons.getAllMeetingAsUnmodifiableObservableList();
    }
}
