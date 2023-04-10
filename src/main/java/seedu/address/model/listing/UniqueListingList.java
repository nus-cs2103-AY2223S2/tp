package seedu.address.model.listing;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.listing.exceptions.DuplicateListingException;
import seedu.address.model.listing.exceptions.ListingNotFoundException;

/**
 * A list of listing that enforces uniqueness between its elements and does not allow nulls.
 * A listing is considered unique by comparing using {@code Listing#isSameListing(Listing)}. As such, adding and
 * updating of listings uses Listing#isSameListing(Listing) for equality so as to ensure that the listing being added or
 * updated is unique in terms of identity in the UniqueListingList. However, the removal of a listing uses
 * Listing#equals(Object) so as to ensure that the listing with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Listing#isSameListing(Listing)
 */
public class UniqueListingList implements Iterable<Listing> {

    private final ObservableList<Listing> internalList = FXCollections.observableArrayList();
    private final ObservableList<Listing> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent listing as the given argument.
     */
    public boolean contains(Listing toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameListing);
    }

    /**
     * Adds a listing to the list.
     * The listing must not already exist in the list.
     */
    public void add(Listing toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateListingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the listing {@code target} in the list with {@code editedListing}.
     * {@code target} must exist in the list.
     * The listing identity of {@code editedListing} must not be the same as another existing listing in the list.
     */
    public void setListing(Listing target, Listing editedListing) {
        requireAllNonNull(target, editedListing);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ListingNotFoundException();
        }

        if (!target.isSameListing(editedListing) && contains(editedListing)) {
            throw new DuplicateListingException();
        }

        internalList.set(index, editedListing);
    }

    /**
     * Removes the equivalent listing from the list.
     * The listing must exist in the list.
     */
    public void remove(Listing toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ListingNotFoundException();
        }
    }

    public void setListings(UniqueListingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setListings(List<Listing> listings) {
        requireAllNonNull(listings);
        if (!listingsAreUnique(listings)) {
            throw new DuplicateListingException();
        }

        internalList.setAll(listings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Listing> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Listing> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueListingList // instanceof handles nulls
                        && internalList.equals(((UniqueListingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code listings} contains only unique listings.
     */
    private boolean listingsAreUnique(List<Listing> listings) {
        for (int i = 0; i < listings.size() - 1; i++) {
            for (int j = i + 1; j < listings.size(); j++) {
                if (listings.get(i).isSameListing(listings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
