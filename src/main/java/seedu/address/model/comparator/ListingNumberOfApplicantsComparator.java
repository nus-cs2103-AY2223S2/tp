package seedu.address.model.comparator;

import java.util.Comparator;

import seedu.address.model.listing.Listing;

/**
 * Compares listing according to the number of applicants to sort them alphabetically.
 */
public class ListingNumberOfApplicantsComparator implements Comparator<Listing> {
    @Override
    public int compare(Listing o1, Listing o2) {
        return o1.getApplicants().size() - o2.getApplicants().size();
    }
}
