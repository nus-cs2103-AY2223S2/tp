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
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FriendlyLink implements ReadOnlyFriendlyLink {

    private final UniquePersonList persons;
    private final UniquePairList pairs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        pairs = new UniquePairList();
    }

    public FriendlyLink() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
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
     * Replaces the contents of the pair list with {@code pairs}.
     * {@code pairs} must not contain duplicate pairs.
     */
    public void setPairs(List<Pair> pairs) {
        this.pairs.setPairs((pairs));
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFriendlyLink newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPairs(newData.getPairList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in friendly link.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to friendly link.
     * The person must not already exist in friendly link.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in friendly link.
     * The person identity of {@code editedPerson} must not be the same as another existing person in friendly link.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Retrieves the elderly with the given nric.
     * The elderly with such a nric must exist in friendly link.
     *
     * @param nric Nric of the elderly.
     * @return The elderly with the name.
     */
    public Elderly getElderly(Nric nric) {
        requireNonNull(nric);
        return persons.getElderly(nric);
    }

    /**
     * Retrieves the volunteer with the given nric.
     * The volunteer with such a nric must exist in friendly link.
     *
     * @param nric Nric of the volunteer.
     * @return The volunteer with the name.
     */
    public Volunteer getVolunteer(Nric nric) {
        requireNonNull(nric);
        return persons.getVolunteer(nric);
    }
    /**
     * Removes {@code key} from this {@code FriendlyLink}.
     * {@code key} must exist in friendly link.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// pair-level operations

    /**
     * Returns true if a pair with the same identity as {@code pair} exists in friendly link.
     */
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return pairs.contains(pair);
    }

    /**
     * Adds a pair to friendly link.
     * The pair must not already exist in friendly link.
     */
    public void addPair(Pair p) {
        pairs.add(p);
    }

    /**
     * Replaces the given pair {@code target} in the list with {@code editedPair}.
     * {@code target} must exist in friendly link.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in friendly link.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireNonNull(editedPair);
        pairs.setPair(target, editedPair);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in friendly link.
     */
    public void removePair(Pair key) {
        pairs.remove(key);
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
    public ObservableList<Pair> getPairList() {
        return pairs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendlyLink // instanceof handles nulls
                && persons.equals(((FriendlyLink) other).persons));
        // TODO: check pairs equal other.pairs
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
        // TODO: return Objects.hash(persons, pairs);
    }
}
