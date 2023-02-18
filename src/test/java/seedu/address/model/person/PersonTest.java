package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getGroupTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALBERT.isSamePerson(ALBERT));

        // null -> returns false
        assertFalse(ALBERT.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALBERT).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withGroupTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALBERT.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALBERT).withName(VALID_NAME_BOB).build();
        assertFalse(ALBERT.isSamePerson(editedAlice));

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
        Person aliceCopy = new PersonBuilder(ALBERT).build();
        assertTrue(ALBERT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALBERT.equals(ALBERT));

        // null -> returns false
        assertFalse(ALBERT.equals(null));

        // different type -> returns false
        assertFalse(ALBERT.equals(5));

        // different person -> returns false
        assertFalse(ALBERT.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALBERT).withName(VALID_NAME_BOB).build();
        assertFalse(ALBERT.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALBERT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALBERT.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALBERT).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALBERT.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALBERT).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALBERT.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALBERT).withGroupTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALBERT.equals(editedAlice));
    }
}
