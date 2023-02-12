package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the friendly-link level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FriendlyLink implements ReadOnlyFriendlyLink {
    private final UniquePersonList persons;
    private final UniquePersonList elderly;
    private final UniquePersonList volunteers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        elderly = new UniquePersonList();
        volunteers = new UniquePersonList();
    }

    public FriendlyLink() {}

    /**
     * Creates an FriendlyLink using the Persons in the {@code toBeCopied}
     */
    public FriendlyLink(ReadOnlyFriendlyLink toBeCopied) {
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
     * Replaces the contents of the elderly list with {@code elderly}.
     * {@code elderly} must not contain duplicate elderly.
     */
    public void setElderly(List<Person> elderly) {
        this.elderly.setPersons(elderly);
    }


    /**
     * Replaces the contents of the volunteer list with {@code volunteers}.
     * {@code volunteers} must not contain duplicate volunteers.
     */
    public void setVolunteers(List<Person> volunteers) {
        this.volunteers.setPersons(volunteers);
    }

    /**
     * Resets the existing data of this {@code FriendlyLink} with {@code newData}.
     */
    public void resetData(ReadOnlyFriendlyLink newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setElderly(newData.getElderlyList());
        setVolunteers(newData.getVolunteerList());
    }

    //// person-level operations
    /**
     * Returns true if a person with the same identity as {@code person} exists in the friendlyLink cache.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code elderly} exists in the friendlyLink cache.
     */
    public boolean hasElderly(Person e) {
        requireNonNull(e);
        return elderly.contains(e);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the friendlyLink cache.
     */
    public boolean hasVolunteer(Person volunteer) {
        requireNonNull(volunteer);
        return volunteers.contains(volunteer);
    }

    /**
     * Adds a person to the friendlyLink cache.
     * The person must not already exist in the friendlyLink cache.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds an elderly to the friendlyLink cache.
     * The elderly must not already exist in the friendlyLink cache.
     */
    public void addElderly(Person p) {
        elderly.add(p);
    }

    /**
     * Adds a volunteer to the friendlyLink cache.
     * The volunteer must not already exist in the friendlyLink cache.
     */
    public void addVolunteer(Person p) {
        volunteers.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friendlyLink cache.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the friendlyLink cache.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given elderly {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friendlyLink cache.
     * The elderly identity of {@code editedPerson} must not be the same as another existing elderly in the friendlyLink cache.
     */
    public void setElderly(Person target, Person editedElderly) {
        requireNonNull(editedElderly);
        elderly.setPerson(target, editedElderly);
    }

    /**
     * Replaces the given volunteer {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friendlyLink cache.
     * The volunteer identity of {@code editedPerson} must not be the same as another existing volunteer in the friendlyLink cache.
     */
    public void setVolunteer(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        volunteers.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code FriendlyLink}.
     * {@code key} must exist in the friendlyLink cache.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the elder's cache.
     */
    public void removeElderly(Person key) {
        elderly.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the volunteer's cache.
     */
    public void removeVolunteer(Person key) {
        volunteers.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + elderly.asUnmodifiableObservableList().size() + " elderly"
                + volunteers.asUnmodifiableObservableList().size() + " volunteers";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getElderlyList() {
        return elderly.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getVolunteerList() {
        return volunteers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendlyLink // instanceof handles nulls
                && persons.equals(((FriendlyLink) other).persons)
                && elderly.equals(((FriendlyLink) other).elderly)
                && volunteers.equals(((FriendlyLink) other).volunteers));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + elderly.hashCode() + volunteers.hashCode();
    }
}
