package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.testutil.TypicalListings.CHICKENRICEUNCLE;
import static seedu.address.testutil.TypicalListings.TOILETCLEANER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ListingBuilder;

public class ListingTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(CHICKENRICEUNCLE.isSameListing(CHICKENRICEUNCLE));

        // null -> returns false
        assertFalse(CHICKENRICEUNCLE.isSameListing(null));

        // same name, all other attributes different -> returns true
        Listing editedChickenRiceUncle = new ListingBuilder(CHICKENRICEUNCLE)
                .withDescription(VALID_DESCRIPTION)
                .withApplicants(VALID_APPLICANTS)
                .build();
        assertTrue(CHICKENRICEUNCLE.isSameListing(editedChickenRiceUncle));

        // different name, all other attributes same -> returns false
        editedChickenRiceUncle = new ListingBuilder(CHICKENRICEUNCLE).withTitle(VALID_TITLE).build();
        assertFalse(CHICKENRICEUNCLE.isSameListing(editedChickenRiceUncle));

        // name differs in case, all other attributes same -> returns false
        Listing editedToiletCleaner = new ListingBuilder(TOILETCLEANER)
                .withTitle(TOILETCLEANER.getTitle().toString().toLowerCase())
                .build();
        assertFalse(TOILETCLEANER.isSameListing(editedToiletCleaner));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Listing chickenRiceUncleCopy = new ListingBuilder(CHICKENRICEUNCLE).build();
        assertTrue(CHICKENRICEUNCLE.equals(chickenRiceUncleCopy));

        // same object -> returns true
        assertTrue(CHICKENRICEUNCLE.equals(CHICKENRICEUNCLE));

        // null -> returns false
        assertFalse(CHICKENRICEUNCLE.equals(null));

        // different type -> returns false
        assertFalse(CHICKENRICEUNCLE.equals(5));

        // different person -> returns false
        assertFalse(CHICKENRICEUNCLE.equals(TOILETCLEANER));

        // different name -> returns false
        Listing editedChickenRiceUncle = new ListingBuilder(CHICKENRICEUNCLE).withTitle(VALID_TITLE).build();
        assertFalse(CHICKENRICEUNCLE.equals(editedChickenRiceUncle));

        // different phone -> returns false
        editedChickenRiceUncle = new ListingBuilder(CHICKENRICEUNCLE).withDescription(VALID_DESCRIPTION).build();
        assertFalse(CHICKENRICEUNCLE.equals(editedChickenRiceUncle));

        // different email -> returns true
        editedChickenRiceUncle = new ListingBuilder(CHICKENRICEUNCLE).withApplicants(VALID_APPLICANTS).build();
        assertTrue(CHICKENRICEUNCLE.equals(editedChickenRiceUncle));
    }

    @Test
    public void stringTest() {
        String expected = "Chicken Rice Uncle; Description: Make delicious chicken rice!; Applicants: Tom, Dick, Harry";
        assertTrue(CHICKENRICEUNCLE.toString().equals(expected));
    }
}
