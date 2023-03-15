package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.UniqueFishList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameFish comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueFishList fishes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        fishes = new UniqueFishList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the fishes in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the fish list with {@code fish}.
     * {@code fish} must not contain duplicate fish.
     */
    public void setFishes(List<Fish> fish) {
        this.fishes.setFishes(fish);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setFishes(newData.getFishList());
    }

    //// fish-level operations

    /**
     * Returns true if a fish with the same identity as {@code fish} exists in the address book.
     */
    public boolean hasFish(Fish fish) {
        requireNonNull(fish);
        return fishes.contains(fish);
    }

    /**
     * Adds a fish to the address book.
     * The fish must not already exist in the address book.
     */
    public void addFish(Fish p) {
        fishes.add(p);
    }

    /**
     * Replaces the given fish {@code target} in the list with {@code editedFish}.
     * {@code target} must exist in the address book.
     * The fish identity of {@code editedFish} must not be the same as another existing fish in the address book.
     */
    public void setFish(Fish target, Fish editedFish) {
        requireNonNull(editedFish);

        fishes.setFish(target, editedFish);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeFish(Fish key) {
        fishes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return fishes.asUnmodifiableObservableList().size() + " fishes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Fish> getFishList() {
        return fishes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && fishes.equals(((AddressBook) other).fishes));
    }

    @Override
    public int hashCode() {
        return fishes.hashCode();
    }
}
