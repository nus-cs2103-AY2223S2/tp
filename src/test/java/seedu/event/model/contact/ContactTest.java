package seedu.event.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.event.testutil.TypicalContacts.ALICE;
import static seedu.event.testutil.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.event.testutil.ContactBuilder;

public class ContactTest {

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(ALICE.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // same name, all other attributes different -> returns true
        Contact editedAlice = new ContactBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Contact editedBob = new ContactBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameContact(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ContactBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameContact(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new ContactBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different Contact -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ContactBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }

}
