package seedu.connectus.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_BBA;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;
import static seedu.connectus.testutil.Assert.assertThrows;
import static seedu.connectus.testutil.TypicalPersons.ALICE;
import static seedu.connectus.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.connectus.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getRemarks().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withRemarks(VALID_REMARK_HUSBAND).withModules(VALID_MODULE_CS2101)
                .withCcas(VALID_CCA_ICS).withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withRemarks(VALID_REMARK_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different modules -> returns false
        editedAlice = new PersonBuilder(ALICE).withModules(VALID_MODULE_CS2103T).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ccas -> returns false
        editedAlice = new PersonBuilder(ALICE).withRemarks(VALID_CCA_NES).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ccaPositions -> returns false
        editedAlice = new PersonBuilder(ALICE).withRemarks(VALID_MAJOR_BBA).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
