package seedu.address.model.pair;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalElderlys.AMY;
import static seedu.address.testutil.TypicalElderlys.CHARLIE;
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
        Elderly editedAmy = new ElderlyBuilder(AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(editedAmy).build();
        assertTrue(PAIR1.isSamePair(editedPair1));
        // TODO: check that a different name returns true if nric is the same.

        // different nric, all other attributes same -> returns false
        editedAmy = new ElderlyBuilder(AMY).withNric(VALID_NRIC_BOB).build();
        editedPair1 = new PairBuilder(PAIR1).withElderly(editedAmy).build();
        assertFalse(PAIR1.isSamePair(editedPair1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Pair pair1Copy = new PairBuilder(PAIR1).build();
        assertTrue(PAIR1.equals(pair1Copy));

        // same object -> returns true
        assertTrue(PAIR1.equals(PAIR1));

        // null -> returns false
        assertFalse(PAIR1.equals(null));

        // different type -> returns false
        assertFalse(PAIR1.equals(5));

        // different pair -> returns false
        assertFalse(PAIR1.equals(PAIR2));

        // different elderly -> returns false
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(CHARLIE).build();
        assertFalse(PAIR1.equals(editedPair1));

        // different volunteer -> returns false
        editedPair1 = new PairBuilder(PAIR1).withVolunteer(DANIEL).build();
        assertFalse(PAIR1.equals(editedPair1));
    }
}
