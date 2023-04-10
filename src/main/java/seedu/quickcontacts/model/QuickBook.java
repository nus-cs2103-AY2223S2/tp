package seedu.quickcontacts.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.meeting.UniqueMeetingList;
import seedu.quickcontacts.model.person.Name;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.model.person.UniquePersonList;

/**
 * Wraps all data at the quick-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class QuickBook implements ReadOnlyQuickBook {

    private final UniquePersonList persons;
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        meetings = new UniqueMeetingList();
    }

    public QuickBook() {
    }

    /**
     * Creates an QuickBook using the Persons in the {@code toBeCopied}
     */
    public QuickBook(ReadOnlyQuickBook toBeCopied) {
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
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code QuickBook} with {@code newData}.
     */
    public void resetData(ReadOnlyQuickBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setMeetings(newData.getMeetingList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the quick book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the quick book.
     * The person must not already exist in the quick book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    public Person getPersonByName(Name personName) {
        requireNonNull(personName);
        return persons.getPersonByName(personName);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the quick book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the quick book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code QuickBook}.
     * {@code key} must exist in the quick book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// meeting-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the quick book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the quick book.
     * The meeting must not already exist in the quick book.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Sort Meeting in quickbook.
     */
    public void sortMeeting(Comparator comparator) {
        meetings.sort(comparator);
    }

    /**
     * return hashmap of meeting that have person to edit (key) and string of current attendees edited person(value).
     */
    public HashMap<String, String> indexAttendees(Person personToEdit, Person target) {
        return meetings.indexAttendees(personToEdit, target);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the quick book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the quick
     * book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code QuickBook}.
     * {@code key} must exist in the quick book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n"
            + meetings.asUnmodifiableObservableList().size() + " meetings";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof QuickBook // instanceof handles nulls
            && persons.equals(((QuickBook) other).persons)
            && meetings.equals(((QuickBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, meetings);
    }
}
