package seedu.techtrack.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalRoles.ALICE;
import static seedu.techtrack.testutil.TypicalRoles.BOB;

import org.junit.jupiter.api.Test;

import seedu.techtrack.testutil.RoleBuilder;

public class RoleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Role role = new RoleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> role.getTags().remove(0));
    }

    @Test
    public void isSameRole() {
        // same object -> returns true
        assertTrue(ALICE.isSameRole(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRole(null));

        // same name and company, all other attributes different -> returns true
        Role editedAlice = new RoleBuilder(ALICE).withPhone(VALID_CONTACT_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRole(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new RoleBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameRole(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Role editedBob = new RoleBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameRole(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new RoleBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameRole(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Role aliceCopy = new RoleBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different role -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Role editedAlice = new RoleBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new RoleBuilder(ALICE).withPhone(VALID_CONTACT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new RoleBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new RoleBuilder(ALICE).withCompany(VALID_COMPANY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new RoleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different experience -> returns false
        editedAlice = new RoleBuilder(ALICE).withExperience(VALID_EXPERIENCE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
