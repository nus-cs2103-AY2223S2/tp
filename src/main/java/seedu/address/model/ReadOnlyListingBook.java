package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyListingBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Listing> getListingList();

}
