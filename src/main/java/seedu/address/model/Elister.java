package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Tag;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the E-Lister level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Elister implements ReadOnlyElister, DeepCopyable<Elister> {

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

    public Elister() {}

    /**
     * Creates an Elister using the Persons in the {@code toBeCopied}
     */
    public Elister(ReadOnlyElister toBeCopied) {
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
     * Resets the existing data of this {@code Elister} with {@code newData}.
     */
    public void resetData(ReadOnlyElister newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the E-Lister.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a tag to the person in the E-Lister
     * Person must already exist in the E-Lister
     *
     * @param person the person to add the tag to
     * @param tag the tag added to the person
     */
    public void addTag(Person person, Tag tag) {
        persons.addTag(person, tag);
    }

    /**
     * Deletes a tag from the person in the E-Lister
     * Person and the tag must already exist in the E-Lister
     *
     * @param person the person to delete tag from
     * @param tag the tag to be deleted
     */
    public void deleteTag(Person person, Tag tag) {
        persons.deleteTag(person, tag);
    }

    /**
     * Adds a person to the E-Lister.
     * The person must not already exist in the E-Lister.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the E-Lister.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the E-Lister.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Elister}.
     * {@code key} must exist in the E-Lister.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    @Override
    public Elister deepCopy() {
        Elister copy = new Elister();
        for (Person p: persons.asUnmodifiableObservableList()) {
            copy.addPerson(p.deepCopy());
        }
        return copy;
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
                || (other instanceof Elister // instanceof handles nulls
                && persons.equals(((Elister) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
