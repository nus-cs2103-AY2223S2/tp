package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NO_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NO_PLATFORMS;
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
            .withApplicants(VALID_NO_APPLICANTS)
            .withPlatforms(VALID_NO_PLATFORMS)
            .build();
    public static final Listing TOILET_CLEANER = new ListingBuilder()
            .withTitle("Toilet Cleaner")
            .withDescription("Cleans toilets really quickly and well")
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .withPlatforms(new ArrayList<>(TypicalPlatforms.getTypicalPlatforms()))
            .build();
    public static final Listing SOFTWARE_DEVELOPER = new ListingBuilder()
            .withTitle(VALID_TITLE)
            .withDescription(VALID_DESCRIPTION)
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .withPlatforms(new ArrayList<>(TypicalPlatforms.getTypicalPlatforms()))
            .build();
    public static final Listing MEDIA_DEVELOPER = new ListingBuilder()
            .withTitle("Media Developer")
            .withDescription("Help with the development of media")
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .withPlatforms(new ArrayList<>(TypicalPlatforms.getTypicalPlatforms()))
            .build();
    public static final Listing GAME_DEVELOPER = new ListingBuilder()
            .withTitle("Game Developer")
            .withDescription("Help to create new PS5 games")
            .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
            .withPlatforms(new ArrayList<>(TypicalPlatforms.getTypicalPlatforms()))
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
        return new ArrayList<>(Arrays.asList(CHICKEN_RICE_UNCLE, TOILET_CLEANER, MEDIA_DEVELOPER,
                GAME_DEVELOPER, SOFTWARE_DEVELOPER));
    }
}
