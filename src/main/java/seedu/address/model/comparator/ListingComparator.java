package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.listing.Listing;

/**
 * Sorts the list according to the available listing comparators.
 */
public enum ListingComparator implements Comparator<Listing> {
    TITLE(new ListingTitleComparator()),
    DESCRIPTION(new ListingDescriptionComparator()),
    APPLICANTS(new ListingNumberOfApplicantsComparator()),
    NONE((listing1, listing2) -> 0);

    public static final String MESSAGE_CONSTRAINTS = "Field must only be one of these values: "
            + "NONE, TITLE, DESCRIPTION, APPLICANTS";

    private Comparator<Listing> comparator;

    /**
     * Creates a ListingComparator that compares two listings.
     * @param comparator the logic used to compare the listings
     */
    ListingComparator(Comparator<Listing> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(Listing listing1, Listing listing2) {
        return comparator.compare(listing1, listing2);
    }
}
