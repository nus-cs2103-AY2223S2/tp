package seedu.ultron.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.UniqueOpeningList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameOpening comparison)
 */
public class Ultron implements ReadOnlyUltron {

    private final UniqueOpeningList openings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        openings = new UniqueOpeningList();
    }

    public Ultron() {
    }

    /**
     * Creates an Ultron using the Openings in the {@code toBeCopied}
     */
    public Ultron(ReadOnlyUltron toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the opening list with {@code openings}.
     * {@code openings} must not contain duplicate openings.
     */
    public void setOpenings(List<Opening> openings) {
        this.openings.setOpenings(openings);
    }

    /**
     * Resets the existing data of this {@code Ultron} with {@code newData}.
     */
    public void resetData(ReadOnlyUltron newData) {
        requireNonNull(newData);
        setOpenings(newData.getOpeningList());
    }

    //// opening-level operations

    /**
     * Returns true if a opening with the same identity as {@code opening} exists in the address book.
     */
    public boolean hasOpening(Opening opening) {
        requireNonNull(opening);
        return openings.contains(opening);
    }

    /**
     * Adds a opening to the address book.
     * The opening must not already exist in the address book.
     */
    public void addOpening(Opening p) {
        openings.add(p);
    }

    /**
     * Replaces the given opening {@code target} in the list with {@code editedOpening}.
     * {@code target} must exist in the address book.
     * The opening identity of {@code editedOpening} must not be the same as another
     * existing opening in the address book.
     */
    public void setOpening(Opening target, Opening editedOpening) {
        requireNonNull(editedOpening);

        openings.setOpening(target, editedOpening);
    }

    /**
     * Removes {@code key} from this {@code Ultron}.
     * {@code key} must exist in the address book.
     */
    public void removeOpening(Opening key) {
        openings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return openings.asUnmodifiableObservableList().size() + " openings";
    }

    @Override
    public ObservableList<Opening> getOpeningList() {
        return openings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ultron // instanceof handles nulls
                && openings.equals(((Ultron) other).openings));
    }

    @Override
    public int hashCode() {
        return openings.hashCode();
    }
}
