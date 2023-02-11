package seedu.address.model.pair;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPairs.PAIR2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PairBuilder;
import seedu.address.testutil.PersonBuilder;

public class PairTest {

    @Test
    public void isSamePair() {
        // same object -> returns true
        assertTrue(PAIR1.isSamePair(PAIR1));

        // null -> returns false
        assertFalse(PAIR1.isSamePair(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(editedAlice).build();
        assertTrue(PAIR1.isSamePair(editedPair1));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_AMY).build();
        editedPair1 = new PairBuilder(PAIR1).withElderly(editedAlice).build();
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
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(CARL).build();
        assertFalse(PAIR1.equals(editedPair1));

        // different volunteer -> returns false
        editedPair1 = new PairBuilder(PAIR1).withVolunteer(DANIEL).build();
        assertFalse(PAIR1.equals(editedPair1));
    }
}
