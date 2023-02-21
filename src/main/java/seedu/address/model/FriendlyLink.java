package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.UniquePairList;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the friendly-link level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FriendlyLink implements ReadOnlyFriendlyLink {
    // TODO: update generic to volunteer and remove person list
    private final UniquePersonList<Person> persons;
    private final UniquePersonList<Elderly> elderly;
    private final UniquePersonList<Person> volunteers;
    private final UniquePairList pairs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList<>();
        pairs = new UniquePairList();
        elderly = new UniquePersonList<>();
        volunteers = new UniquePersonList<>();
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
    public void setAllElderly(List<Elderly> elderly) {
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
        setAllElderly(newData.getElderlyList());
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
    public boolean hasElderly(Elderly e) {
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
    public void addElderly(Elderly p) {
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
     * The person identity of {@code editedPerson} must not be the same as
     * another existing person in the friendlyLink cache.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given elderly {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friendlyLink cache.
     * The elderly identity of {@code editedPerson} must not be the same as
     * another existing elderly in the friendlyLink cache.
     */
    public void setElderly(Elderly target, Elderly editedElderly) {
        requireNonNull(editedElderly);
        elderly.setPerson(target, editedElderly);
    }

    /**
     * Replaces the given volunteer {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friendlyLink cache.
     * The volunteer identity of {@code editedPerson} must not be the same as
     * another existing volunteer in the friendlyLink cache.
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

    //// pair-level operations

    /**
     * Returns true if a pair with the same identity as {@code pair} exists in the address book.
     */
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return pairs.contains(pair);
    }

    /**
     * Adds a pair to the address book.
     * The pair must not already exist in the address book.
     */
    public void addPair(Pair p) {
        pairs.add(p);
    }

    /**
     * Replaces the given pair {@code target} in the list with {@code editedPair}.
     * {@code target} must exist in the address book.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in the address book.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireNonNull(editedPair);
        pairs.setPair(target, editedPair);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePair(Pair key) {
        pairs.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the elderly's list.
     */
    public void removeElderly(Elderly key) {
        elderly.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the volunteer's list.
     */
    public void removeVolunteer(Person key) {
        volunteers.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + elderly.asUnmodifiableObservableList().size() + " elderly"
                + volunteers.asUnmodifiableObservableList().size() + " volunteers"
                + pairs.asUnmodifiableObservableList().size() + " pairs";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Elderly> getElderlyList() {
        return elderly.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getVolunteerList() {
        return volunteers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pair> getPairList() {
        return pairs.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendlyLink // instanceof handles nulls
                && persons.equals(((FriendlyLink) other).persons)
                && elderly.equals(((FriendlyLink) other).elderly)
                && volunteers.equals(((FriendlyLink) other).volunteers)
                && pairs.equals(((FriendlyLink) other).pairs));
    }

    @Override
    public int hashCode() {
        return persons.hashCode()
                + elderly.hashCode()
                + volunteers.hashCode()
                + pairs.hashCode();
    }
}
