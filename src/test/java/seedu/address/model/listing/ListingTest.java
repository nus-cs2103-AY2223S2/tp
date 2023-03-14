package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NO_APPLICANTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;
import static seedu.address.testutil.TypicalListings.TOILET_CLEANER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.TypicalApplicants;

public class ListingTest {
    @Test
    public void isSameListing() {
        // same object -> returns true
        assertTrue(CHICKEN_RICE_UNCLE.isSameListing(CHICKEN_RICE_UNCLE));

        // null -> returns false
        assertFalse(CHICKEN_RICE_UNCLE.isSameListing(null));

        // same name, all other attributes different -> returns true
        Listing editedChickenRiceUncle = new ListingBuilder(CHICKEN_RICE_UNCLE)
                .withDescription(VALID_DESCRIPTION)
                .withApplicants(VALID_NO_APPLICANTS)
                .build();
        assertTrue(CHICKEN_RICE_UNCLE.isSameListing(editedChickenRiceUncle));

        // different name, all other attributes same -> returns false
        editedChickenRiceUncle = new ListingBuilder(CHICKEN_RICE_UNCLE).withTitle(VALID_TITLE).build();
        assertFalse(CHICKEN_RICE_UNCLE.isSameListing(editedChickenRiceUncle));

        // name differs in case, all other attributes same -> returns false
        Listing editedToiletCleaner = new ListingBuilder(TOILET_CLEANER)
                .withTitle(TOILET_CLEANER.getTitle().toString().toLowerCase())
                .build();
        assertFalse(TOILET_CLEANER.isSameListing(editedToiletCleaner));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Listing chickenRiceUncleCopy = new ListingBuilder(CHICKEN_RICE_UNCLE).build();
        assertTrue(CHICKEN_RICE_UNCLE.equals(chickenRiceUncleCopy));

        // same object -> returns true
        assertTrue(CHICKEN_RICE_UNCLE.equals(CHICKEN_RICE_UNCLE));

        // null -> returns false
        assertFalse(CHICKEN_RICE_UNCLE.equals(null));

        // different type -> returns false
        assertFalse(CHICKEN_RICE_UNCLE.equals(5));

        // different listing -> returns false
        assertFalse(CHICKEN_RICE_UNCLE.equals(TOILET_CLEANER));

        // different title -> returns false
        Listing editedChickenRiceUncle = new ListingBuilder(CHICKEN_RICE_UNCLE).withTitle(VALID_TITLE).build();
        assertFalse(CHICKEN_RICE_UNCLE.equals(editedChickenRiceUncle));

        // different description -> returns false
        editedChickenRiceUncle = new ListingBuilder(CHICKEN_RICE_UNCLE).withDescription(VALID_DESCRIPTION).build();
        assertFalse(CHICKEN_RICE_UNCLE.equals(editedChickenRiceUncle));

        // different applicants -> returns true
        editedChickenRiceUncle = new ListingBuilder(CHICKEN_RICE_UNCLE)
                .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants())).build();
        assertTrue(CHICKEN_RICE_UNCLE.equals(editedChickenRiceUncle));
    }

    @Test
    public void stringTest() {
        String expected = "Chicken Rice Uncle; "
                + "JobDescription: Make delicious chicken rice!";
        assertTrue(CHICKEN_RICE_UNCLE.toString().equals(expected));
    }
}
