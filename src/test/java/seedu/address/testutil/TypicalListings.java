package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.listing.Listing;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalListings {

    public static final Listing CHICKENRICEUNCLE = new ListingBuilder()
            .withTitle("Chicken Rice Uncle")
            .withDescription("Make delicious chicken rice!")
            .withApplicants(new ArrayList<>(Arrays.asList("Tom", "Dick", "Harry")))
            .build();
    public static final Listing TOILETCLEANER = new ListingBuilder()
            .withTitle("Toilet Cleaner")
            .withDescription("Cleans toilets really quickly and well")
            .withApplicants(new ArrayList<>(Arrays.asList("Naruto", "Sasuke", "Sakura")))
            .build();
    public static final Listing SOFTWAREDEVELOPER = new ListingBuilder()
            .withTitle(VALID_TITLE)
            .withDescription(VALID_DESCRIPTION)
            .withApplicants(VALID_APPLICANTS)
            .build();


    private TypicalListings() {} // prevents instantiation


    public static List<Listing> getTypicalListings() {
        return new ArrayList<>(Arrays.asList(CHICKENRICEUNCLE, TOILETCLEANER, SOFTWAREDEVELOPER));
    }
}
