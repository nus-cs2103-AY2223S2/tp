package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.UniquePairList;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * Wraps all data at the friendly-link level
 * Duplicates are not allowed (by .isSamePerson, .isSamePair comparison)
 */
public class FriendlyLink implements ReadOnlyFriendlyLink, ReadOnlyElderly, ReadOnlyVolunteer {
    // TODO: update generic to volunteer and remove person list
    private final UniquePersonList<Person> persons;
    private final UniquePersonList<Elderly> elderly;
    private final UniquePersonList<Volunteer> volunteers;
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
        resetFriendlyLinkData(toBeCopied);
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
     * Replaces the contents of the pair list with {@code pairs}.
     * {@code pairs} must not contain duplicate pairs.
     */
    public void setPairs(List<Pair> pairs) {
        this.pairs.setPairs((pairs));
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
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers.setPersons(volunteers);
    }

    /**
     * Resets the existing data of this {@code FriendlyLink} with {@code newData}.
     */
    public void resetFriendlyLinkData(ReadOnlyFriendlyLink newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setAllElderly(newData.getElderlyList());
        setVolunteers(newData.getVolunteerList());
        setPairs(newData.getPairList());
    }

    /**
     * Resets the existing elderly data of this {@code FriendlyLink} with {@code newData}.
     */
    public void resetElderlyData(ReadOnlyElderly newData) {
        requireNonNull(newData);
        setAllElderly(newData.getElderlyList());
    }

    /**
     * Resets the existing volunteer data of this {@code FriendlyLink} with {@code newData}.
     */
    public void resetVolunteerData(ReadOnlyVolunteer newData) {
        requireNonNull(newData);
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
     * Returns true if an elderly with the same identity as {@code elderly} exists in the friendlyLink cache.
     */
    public boolean hasElderly(Elderly e) {
        requireNonNull(e);
        return elderly.contains(e);
    }

    /**
     * Returns true if a volunteer with the same identity as {@code volunteer} exists in the friendlyLink cache.
     */
    public boolean hasVolunteer(Volunteer volunteer) {
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
    public void addVolunteer(Volunteer p) {
        volunteers.add(p);
    }

    /**
     * Retrieves the elderly with the given nric.
     * The elderly with such a nric must exist in FriendlyLink.
     *
     * @param nric Nric of the elderly.
     * @return The elderly with the name.
     */
    public Elderly getElderly(Nric nric) {
        requireNonNull(nric);
        return elderly.getElderly(nric);
    }

    /**
     * Retrieves the volunteer with the given nric.
     * The volunteer with such a nric must exist in FriendlyLink.
     *
     * @param nric Nric of the volunteer.
     * @return The volunteer with the name.
     */
    public Volunteer getVolunteer(Nric nric) {
        requireNonNull(nric);
        return volunteers.getVolunteer(nric);
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
     * The elderly identity of {@code editedElderly} must not be the same as
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
    public void setVolunteer(Volunteer target, Volunteer editedPerson) {
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
     * {@code key} must exist in the elderly's list.
     */
    public void removeElderly(Elderly key) {
        elderly.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the volunteer's list.
     */
    public void removeVolunteer(Volunteer key) {
        volunteers.remove(key);
    }

    //// pair-level operations

    /**
     * Returns true if a pair with the same identity as {@code pair} exists in FriendlyLink.
     */
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return pairs.contains(pair);
    }

    /**
     * Adds a pair to FriendlyLink.
     * The pair must not already exist in FriendlyLink.
     */
    public void addPair(Pair p) {
        pairs.add(p);
    }

    /**
     * Replaces the given pair {@code target} in the list with {@code editedPair}.
     * {@code target} must exist in FriendlyLink.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in FriendlyLink.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireNonNull(editedPair);
        pairs.setPair(target, editedPair);
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
    public ObservableList<Volunteer> getVolunteerList() {
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
