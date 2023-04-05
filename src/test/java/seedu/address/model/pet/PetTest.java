package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DOG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.EXAMPLE_DOG;
import static seedu.address.testutil.TypicalPets.WHISKERS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PetBuilder;

public class PetTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Pet pet = new PetBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> pet.getTags().remove(0));
    }

    @Test
    public void isSamePet() {
        // same object -> returns true
        assertTrue(WHISKERS.isSamePet(WHISKERS));

        // null -> returns false
        assertFalse(WHISKERS.isSamePet(null));

        // same name, all other attributes different -> returns true
        Pet editedWhiskers = new PetBuilder(WHISKERS).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_DOG).build();
        assertTrue(WHISKERS.isSamePet(editedWhiskers));

        // different name, all other attributes same -> returns false
        editedWhiskers = new PetBuilder(WHISKERS).withName(VALID_NAME_BOB).build();
        assertFalse(WHISKERS.isSamePet(editedWhiskers));

        // name differs in case, all other attributes same -> returns false
        Pet editedBob = new PetBuilder(EXAMPLE_DOG).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(EXAMPLE_DOG.isSamePet(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PetBuilder(EXAMPLE_DOG).withName(nameWithTrailingSpaces).build();
        assertFalse(EXAMPLE_DOG.isSamePet(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Pet whiskersCopy = new PetBuilder(WHISKERS).build();
        assertTrue(WHISKERS.equals(whiskersCopy));

        // same object -> returns true
        assertTrue(WHISKERS.equals(WHISKERS));

        // null -> returns false
        assertFalse(WHISKERS.equals(null));

        // different type -> returns false
        assertFalse(WHISKERS.equals(5));

        // different Pet -> returns false
        assertFalse(WHISKERS.equals(EXAMPLE_DOG));

        // different name -> returns false
        Pet editedWhiskers = new PetBuilder(WHISKERS).withName(VALID_NAME_BOB).build();
        assertFalse(WHISKERS.equals(editedWhiskers));

        // different phone -> returns false
        editedWhiskers = new PetBuilder(WHISKERS).withPhone(VALID_PHONE_BOB).build();
        assertFalse(WHISKERS.equals(editedWhiskers));

        // different email -> returns false
        editedWhiskers = new PetBuilder(WHISKERS).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(WHISKERS.equals(editedWhiskers));

        // different address -> returns false
        editedWhiskers = new PetBuilder(WHISKERS).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(WHISKERS.equals(editedWhiskers));

        // different tags -> returns false
        editedWhiskers = new PetBuilder(WHISKERS).withTags(VALID_TAG_DOG).build();
        assertFalse(WHISKERS.equals(editedWhiskers));
    }
}
