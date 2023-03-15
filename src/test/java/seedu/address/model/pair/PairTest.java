package seedu.address.model.pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalElderly.CHARLIE;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPairs.PAIR2;
import static seedu.address.testutil.TypicalVolunteers.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Elderly;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.PairBuilder;

public class PairTest {

    @Test
    public void isSamePair() {
        // same object -> returns true
        assertTrue(PAIR1.isSamePair(PAIR1));

        // null -> returns false
        assertFalse(PAIR1.isSamePair(null));

        // same nric, all other attributes different -> returns true
        Elderly editedAlice = new ElderlyBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE).build();
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(editedAlice).build();
        assertTrue(PAIR1.isSamePair(editedPair1));
        // TODO: check that a different name returns true if nric is the same.

        // different nric, all other attributes same -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        editedPair1 = new PairBuilder(PAIR1).withElderly(editedAlice).build();
        assertFalse(PAIR1.isSamePair(editedPair1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Pair pair1Copy = new PairBuilder(PAIR1).build();
        assertEquals(PAIR1, pair1Copy);

        // same object -> returns true
        assertEquals(PAIR1, PAIR1);

        // null -> returns false
        assertNotEquals(null, PAIR1);

        // different type -> returns false
        assertNotEquals(5, PAIR1);

        // different pair -> returns false
        assertNotEquals(PAIR1, PAIR2);

        // different elderly -> returns false
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(CHARLIE).build();
        assertNotEquals(PAIR1, editedPair1);

        // different volunteer -> returns false
        editedPair1 = new PairBuilder(PAIR1).withVolunteer(DANIEL).build();
        assertNotEquals(PAIR1, editedPair1);
    }
}
