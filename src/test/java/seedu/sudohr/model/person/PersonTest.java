package seedu.sudohr.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmployee(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEmployee(null));

        // same id, all other attributes different -> returns true
        Employee editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEmployee(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));

        // id has preceding 0s, all other attributes same -> returns true
        String idWithPrecedingZeroes = "00" + VALID_ID_BOB;
        Employee editedBob = new PersonBuilder(BOB).withId(idWithPrecedingZeroes).build();
        assertTrue(BOB.isSameEmployee(editedBob));

        // id has trailing 0s, all other attributes same -> returns false
        String idWithTrailingZeroes = VALID_ID_BOB + "00";
        editedBob = new PersonBuilder(BOB).withId(idWithTrailingZeroes).build();
        assertFalse(BOB.isSameEmployee(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person (different ID) -> returns false
        assertFalse(ALICE.equals(BOB));

        // different id -> returns false
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different sudohr -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void clashes() {
        // same values -> returns true
        Employee aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.clashes(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.clashes(ALICE));

        // null -> returns false
        assertFalse(ALICE.clashes(null));

        // different person -> returns false
        assertFalse(ALICE.clashes(BOB));

        // only same id -> returns true
        Employee editedBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(BOB.clashes(editedBob));

        // only same email -> returns true
        editedBob = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(BOB.clashes(editedBob));

        // only same phone number -> returns true
        editedBob = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.clashes(editedBob));

        // only same name -> returns false
        editedBob = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(BOB.clashes(editedBob));

        // only same address -> returns false
        editedBob = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(BOB.clashes(editedBob));

        // only same tags -> returns false
        // Note bob has the following two tags
        editedBob = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        assertFalse(BOB.clashes(editedBob));
    }
}
