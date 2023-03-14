package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TuteeManagingSystem implements ReadOnlyTuteeManagingSystem {

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

    public TuteeManagingSystem() {}

    /**
     * Creates an TuteeManagingSystem using the Persons in the {@code toBeCopied}
     */
    public TuteeManagingSystem(ReadOnlyTuteeManagingSystem toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tutee list with {@code tutees}.
     * {@code tutees} must not contain duplicate tutees.
     */
    public void setPersons(List<Tutee> tutees) {
        this.persons.setPersons(tutees);
    }

    /**
     * Resets the existing data of this {@code TuteeManagingSystem} with {@code newData}.
     */
    public void resetData(ReadOnlyTuteeManagingSystem newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// tutee-level operations

    /**
     * Returns true if a tutee with the same identity as {@code tutee} exists in the address book.
     */
    public boolean hasPerson(Tutee tutee) {
        requireNonNull(tutee);
        return persons.contains(tutee);
    }

    /**
     * Adds a tutee to the address book.
     * The tutee must not already exist in the address book.
     */
    public void addPerson(Tutee p) {
        persons.add(p);
    }

    /**
     * Replaces the given tutee {@code target} in the list with {@code editedTutee}.
     * {@code target} must exist in the address book.
     * The tutee identity of {@code editedTutee} must not be the same as another existing tutee in the address book.
     */
    public void setPerson(Tutee target, Tutee editedTutee) {
        requireNonNull(editedTutee);

        persons.setPerson(target, editedTutee);
    }

    /**
     * Removes {@code key} from this {@code TuteeManagingSystem}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Tutee key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Tutee> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TuteeManagingSystem // instanceof handles nulls
                && persons.equals(((TuteeManagingSystem) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
