package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.UniqueListingList;

/**
 * Wraps all data at the listing-book level
 * Duplicates are not allowed (by .isSameListing comparison)
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
     * Creates an ListingBook using the Listings in the {@code toBeCopied}
     */
    public ListingBook(ReadOnlyListingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the listing list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setListings(List<Listing> listings) {
        this.listings.setListings(listings);
    }

    /**
     * Resets the existing data of this {@code ListingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyListingBook newData) {
        requireNonNull(newData);

        setListings(newData.getListingList());
    }

    //// listing-level operations

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the listing book.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Adds a listing to the listing book.
     * The person must not already exist in the listing book.
     */
    public void addListing(Listing l) {
        listings.add(l);
    }

    /**
     * Replaces the given listing {@code target} in the list with {@code editedListing}.
     * {@code target} must exist in the listing book.
     * The listing identity of {@code editedListing} must not be the same as
     * another existing listing in the listing book.
     */
    public void setListing(Listing target, Listing editedListing) {
        requireNonNull(editedListing);

        listings.setListing(target, editedListing);
    }

    /**
     * Removes {@code key} from this {@code ListingBook}.
     * {@code key} must exist in the listing book.
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
