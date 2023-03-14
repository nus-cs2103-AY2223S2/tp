package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.UniqueListingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ListingBook implements ReadOnlyListingBook {

    private final UniqueListingList listings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        listings = new UniqueListingList();
    }

    public ListingBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ListingBook(ReadOnlyListingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setListings(List<Listing> listings) {
        this.listings.setListings(listings);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyListingBook newData) {
        requireNonNull(newData);

        setListings(newData.getListingList());
    }

    //// listing-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addListing(Listing l) {
        listings.add(l);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setListing(Listing target, Listing editedListing) {
        requireNonNull(editedListing);

        listings.setListing(target, editedListing);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeListing(Listing key) {
        listings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return listings.asUnmodifiableObservableList().size() + " listings";
        // TODO: refine later
    }

    @Override
    public ObservableList<Listing> getListingList() {
        return listings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListingBook // instanceof handles nulls
                && listings.equals(((ListingBook) other).listings));
    }

    @Override
    public int hashCode() {
        return listings.hashCode();
    }
}
