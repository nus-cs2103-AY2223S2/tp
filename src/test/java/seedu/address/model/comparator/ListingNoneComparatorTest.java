package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.listing.Listing;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ListingBuilder;

public class ListingNoneComparatorTest {

    @Test
    public void compare_listingWithEverythingSame_returnsZero() {
        Listing l1 = new ListingBuilder().build();
        Listing l2 = new ListingBuilder().build();
        ListingComparator comparator = ListingComparator.NONE;
        assertEquals(0, comparator.compare(l1, l2));
    }

    @Test
    public void compare_listingWithDifferentTitle_returnsZero() {
        Listing l1 = new ListingBuilder().withTitle("Software Developer").build();
        Listing l2 = new ListingBuilder().withTitle("Web Developer").build();
        ListingComparator comparator = ListingComparator.NONE;
        assertEquals(0, comparator.compare(l1, l2));
    }

    @Test
    public void compare_listingWithDifferentDescription_returnsZero() {
        Listing l1 = new ListingBuilder().withDescription("Looking for software").build();
        Listing l2 = new ListingBuilder().withDescription("Looking for web").build();
        ListingComparator comparator = ListingComparator.NONE;
        assertEquals(0, comparator.compare(l1, l2));
    }

    @Test
    public void compare_listingWithDifferentNumberOfApplicants_returnsZero() {
        Listing l1 = new ListingBuilder()
                .withApplicants(new ArrayList<>(Arrays.asList(new ApplicantBuilder().build())))
                .build();
        Listing l2 = new ListingBuilder()
                .withApplicants(new ArrayList<>(Arrays.asList(
                        new ApplicantBuilder().build(),
                        new ApplicantBuilder().build())))
                .build();
        ListingComparator comparator = ListingComparator.NONE;
        assertEquals(0, comparator.compare(l1, l2));
    }
}
