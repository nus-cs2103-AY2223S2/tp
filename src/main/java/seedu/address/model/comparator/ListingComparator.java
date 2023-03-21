package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.listing.Listing;

public enum ListingComparator implements Comparator<Listing> {
    TITLE(new ListingTitleComparator()),
    DESCRIPTION(new ListingDescriptionComparator()),
    NUMBER_OF_APPLICANTS(new ListingNumberOfApplicantsComparator());

    private Comparator<Listing> comparator;

    ListingComparator(Comparator<Listing> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(Listing listing1, Listing listing2) {
        return comparator.compare(listing1, listing2);
    }
}
