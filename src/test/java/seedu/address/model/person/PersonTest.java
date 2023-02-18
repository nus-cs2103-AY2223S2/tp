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
        Person editedAlbert = new PersonBuilder(ALBERT).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withGroupTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALBERT.isSamePerson(editedAlbert));

        // different name, all other attributes same -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withName(VALID_NAME_BOB).build();
        assertFalse(ALBERT.isSamePerson(editedAlbert));

        // name differs in case, all other attributes same -> returns false
        Person editedBart = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBart));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBart = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBart));
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
        Person editedAlbert = new PersonBuilder(ALBERT).withName(VALID_NAME_BOB).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different phone -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different email -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different address -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALBERT.equals(editedAlbert));

        // different tags -> returns false
        editedAlbert = new PersonBuilder(ALBERT).withGroupTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALBERT.equals(editedAlbert));
    }
}
