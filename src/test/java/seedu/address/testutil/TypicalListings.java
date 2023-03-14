package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ListingBook;
import seedu.address.model.listing.Listing;


/**
 * A utility class containing a list of {@code Listing} objects to be used in tests.
 */
public class TypicalListings {

    public static final Listing CHICKEN_RICE_UNCLE = new ListingBuilder()
            .withTitle("Chicken Rice Uncle")
            .withDescription("Make delicious chicken rice!")
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .build();
    public static final Listing TOILET_CLEANER = new ListingBuilder()
            .withTitle("Toilet Cleaner")
            .withDescription("Cleans toilets really quickly and well")
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .build();
    public static final Listing SOFTWARE_DEVELOPER = new ListingBuilder()
            .withTitle(VALID_TITLE)
            .withDescription(VALID_DESCRIPTION)
            .withApplicants(VALID_APPLICANTS)
            .build();

    private TypicalListings() {} // prevents instantiation

    /**
     * Returns an {@code ListingBook} with all the typical listings.
     */
    public static ListingBook getTypicalListingBook() {
        ListingBook lb = new ListingBook();
        for (Listing listing : getTypicalListings()) {
            lb.addListing(listing);
        }
        return lb;
    }

    public static List<Listing> getTypicalListings() {
        return new ArrayList<>(Arrays.asList(CHICKEN_RICE_UNCLE, TOILET_CLEANER, SOFTWARE_DEVELOPER));
    }
}
