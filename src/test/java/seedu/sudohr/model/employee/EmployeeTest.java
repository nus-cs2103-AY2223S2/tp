package seedu.sudohr.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.BOB;

import org.junit.jupiter.api.Test;

import seedu.sudohr.testutil.EmployeeBuilder;

public class EmployeeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee employee = new EmployeeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> employee.getTags().remove(0));
    }

    @Test
    public void isSameEmployee() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmployee(ALICE));

        // copy of the object
        Employee copyAlice = new EmployeeBuilder(ALICE).build();
        assertTrue(ALICE.isSameEmployee(copyAlice));

        // null -> returns false
        assertFalse(ALICE.isSameEmployee(null));

        // same id, all other attributes different -> returns true
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameEmployee(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));

        // id has preceding 0s, all other attributes same -> returns true
        String idWithPrecedingZeroes = "00" + VALID_ID_BOB;
        Employee editedBob = new EmployeeBuilder(BOB).withId(idWithPrecedingZeroes).build();
        assertTrue(BOB.isSameEmployee(editedBob));

        // id has trailing 0s, all other attributes same -> returns false
        String idWithTrailingZeroes = VALID_ID_BOB + "00";
        editedBob = new EmployeeBuilder(BOB).withId(idWithTrailingZeroes).build();
        assertFalse(BOB.isSameEmployee(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different employee (different ID) -> returns false
        assertFalse(ALICE.equals(BOB));

        // different id -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different sudohr -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void emailClashes() {
        // same values -> returns false
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertFalse(ALICE.emailClashes(aliceCopy));

        // same object -> returns false
        assertFalse(ALICE.emailClashes(ALICE));

        // null -> returns false
        assertFalse(ALICE.emailClashes(null));

        // same id only -> returns false
        Employee editedBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same email only -> returns true
        editedBob = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(BOB.emailClashes(editedBob));

        // same phone number only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same name only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same address only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same tags only -> returns false
        // Note bob has the following two tags
        editedBob = new EmployeeBuilder(ALICE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same name, phone, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same id, name, phone, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(BOB.emailClashes(editedBob));

        // same email, name, phone, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).build();
        assertTrue(BOB.emailClashes(editedBob));
    }

    @Test
    public void phoneClashes() {
        // same values -> returns false
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertFalse(ALICE.phoneClashes(aliceCopy));

        // same object -> returns false
        assertFalse(ALICE.phoneClashes(ALICE));

        // null -> returns false
        assertFalse(ALICE.phoneClashes(null));

        // same id only -> returns false
        Employee editedBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same email only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same phone number only -> returns true
        editedBob = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.phoneClashes(editedBob));

        // same name only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same address only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same tags only -> returns false
        // Note bob has the following two tags
        editedBob = new EmployeeBuilder(ALICE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same name, email, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).withPhone(VALID_PHONE_AMY).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same id, name, email, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY).build();
        assertFalse(BOB.phoneClashes(editedBob));

        // same email, name, phone, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).build();
        assertTrue(BOB.phoneClashes(editedBob));
    }

    @Test
    public void clashes() {
        // same values -> returns false since same id hence treated as same employee
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertFalse(ALICE.clashes(aliceCopy));

        // same object -> returns false
        assertFalse(ALICE.clashes(ALICE));

        // null -> returns false
        assertFalse(ALICE.clashes(null));

        // same id only -> returns false
        Employee editedBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(BOB.clashes(editedBob));

        // same id, name, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(BOB.clashes(editedBob));

        // same email only -> returns true
        editedBob = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(BOB.clashes(editedBob));

        // same phone number only -> returns true
        editedBob = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.clashes(editedBob));

        // same name only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(BOB.clashes(editedBob));

        // same address only -> returns false
        editedBob = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(BOB.clashes(editedBob));

        // same tags only -> returns false
        // Note bob has the following two tags
        editedBob = new EmployeeBuilder(ALICE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        assertFalse(BOB.clashes(editedBob));

        // same name, address, tags only -> returns false
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        assertFalse(BOB.clashes(editedBob));

        // same email, name, address, tags only -> returns true
        editedBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY).withId(VALID_ID_AMY).build();
        assertTrue(BOB.clashes(editedBob));

        // same phone number, name, address, tags only -> returns true
        editedBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertTrue(BOB.clashes(editedBob));


    }
}
