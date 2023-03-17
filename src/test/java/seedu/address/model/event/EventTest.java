package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event person = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(ALICE.isSameEvent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedAlice = new EventBuilder(ALICE).withRate(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEvent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EventBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameEvent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Event editedBob = new EventBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameEvent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EventBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameEvent(editedBob));

        // same name, different timing -> returns false
        editedAlice = new EventBuilder(ALICE)
                .withStartTime(VALID_START_TIME_AMY)
                .withEndTime(VALID_END_TIME_AMY)
                .build();
        assertFalse(ALICE.isSameEvent(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceCopy = new EventBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different event -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Event editedAlice = new EventBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EventBuilder(ALICE).withRate(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EventBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EventBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        assertFalse(ALICE.hashCode() == (BOB.hashCode()));

        // different time -> return false
        editedAlice = new EventBuilder(ALICE)
                .withStartTime(VALID_START_TIME_AMY)
                .withEndTime(VALID_END_TIME_AMY).build();
        assertFalse(ALICE.equals(editedAlice));


    }
}
