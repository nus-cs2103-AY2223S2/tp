package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;

public class ListingTitleComparatorTest {

    @Test
    public void compare_listingWithSameTitle_returnsZero() {
        Listing l1 = new ListingBuilder().withDescription("Looking for Software Developer").build();
        Listing l2 = new ListingBuilder().withDescription("Looking for Web Developer").build();
        ListingTitleComparator comparator = new ListingTitleComparator();
        assertEquals(0, comparator.compare(l1, l2));
    }

    @Test
    public void compare_listingWithDifferentTitle_returnsNegative() {
        Listing l1 = new ListingBuilder().withTitle("Software Developer").build();
        Listing l2 = new ListingBuilder().withTitle("Web Developer").build();
        ListingTitleComparator comparator = new ListingTitleComparator();
        int result = comparator.compare(l1, l2);
        assertEquals(true, result < 0);
    }

    @Test
    public void compare_listingWithDifferentTitle_returnsPositive() {
        Listing l1 = new ListingBuilder().withTitle("Web Developer").build();
        Listing l2 = new ListingBuilder().withTitle("Software Developer").build();
        ListingTitleComparator comparator = new ListingTitleComparator();
        int result = comparator.compare(l1, l2);
        assertEquals(true, result > 0);
    }

}
