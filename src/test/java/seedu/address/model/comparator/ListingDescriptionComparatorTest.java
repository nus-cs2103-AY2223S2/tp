package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;

public class ListingDescriptionComparatorTest {

    @Test
    public void compare_listingWithSameDescription_returnsZero() {
        Listing l1 = new ListingBuilder().withTitle("Software Developer").build();
        Listing l2 = new ListingBuilder().withTitle("Web Developer").build();
        ListingDescriptionComparator comparator = new ListingDescriptionComparator();
        assertEquals(0, comparator.compare(l1, l2));
    }

    @Test
    public void compare_listingWithDifferentDescription_returnsNegative() {
        Listing l1 = new ListingBuilder().withDescription("Looking for software kid.").build();
        Listing l2 = new ListingBuilder().withDescription("Looking for web dev kid.").build();
        ListingDescriptionComparator comparator = new ListingDescriptionComparator();
        int result = comparator.compare(l1, l2);
        assertEquals(true, result < 0);
    }

    @Test
    public void compare_listingWithDifferentDescription_returnsPositive() {
        Listing l1 = new ListingBuilder().withDescription("Looking for web dev kid.").build();
        Listing l2 = new ListingBuilder().withDescription("Looking for software kid.").build();
        ListingDescriptionComparator comparator = new ListingDescriptionComparator();
        int result = comparator.compare(l1, l2);
        assertEquals(true, result > 0);
    }

}
