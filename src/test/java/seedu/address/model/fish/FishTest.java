package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDING_INTERVAL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.ALICE;
import static seedu.address.testutil.TypicalFishes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FishBuilder;

public class FishTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Fish fish = new FishBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> fish.getTags().remove(0));
    }

    @Test
    public void isSameFish() {
        // same object -> returns true
        assertTrue(ALICE.isSameFish(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameFish(null));

        // same name, all other attributes different -> returns true
        Fish editedAlice = new FishBuilder(ALICE).withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_BOB).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFish(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new FishBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameFish(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Fish editedBob = new FishBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameFish(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new FishBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameFish(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Fish aliceCopy = new FishBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different fish -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Fish editedAlice = new FishBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different lastFedDate -> returns false
        editedAlice = new FishBuilder(ALICE).withLastFedDate(VALID_LAST_FED_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different species -> returns false
        editedAlice = new FishBuilder(ALICE).withSpecies(VALID_SPECIES_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different FeedingInterval -> returns false
        editedAlice = new FishBuilder(ALICE).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FishBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
