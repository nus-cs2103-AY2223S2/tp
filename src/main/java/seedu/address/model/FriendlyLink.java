package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.UniquePairList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.information.Name;

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
     * Retrieves the person with the given name.
     * The person with such a name must exist in the address book.
     *
     * @param name Name of the person.
     * @return The person with the name.
     */
    public Person getPerson(Name name) {
        requireNonNull(name);
        return persons.get(name);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
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
