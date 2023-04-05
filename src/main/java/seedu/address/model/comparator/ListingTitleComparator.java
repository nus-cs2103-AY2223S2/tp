package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.listing.Listing;

/**
 * Compares listing according to the title to sort them alphabetically.
 */
public class ListingTitleComparator implements Comparator<Listing> {
    @Override
    public int compare(Listing o1, Listing o2) {
        return o1.getTitle().fullTitle.toLowerCase().compareTo(
                o2.getTitle().fullTitle.toLowerCase()
        );
    }
}
