package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DOG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.WHISKERS;
import static seedu.address.testutil.TypicalPets.EXAMPLE_DOG;

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
        Pet editedWHISKERS = new PetBuilder(WHISKERS).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_DOG).build();
        assertTrue(WHISKERS.isSamePet(editedWHISKERS));

        // different name, all other attributes same -> returns false
        editedWHISKERS = new PetBuilder(WHISKERS).withName(VALID_NAME_BOB).build();
        assertFalse(WHISKERS.isSamePet(editedWHISKERS));

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
        Pet WHISKERSCopy = new PetBuilder(WHISKERS).build();
        assertTrue(WHISKERS.equals(WHISKERSCopy));

        // same object -> returns true
        assertTrue(WHISKERS.equals(WHISKERS));

        // null -> returns false
        assertFalse(WHISKERS.equals(null));

        // different type -> returns false
        assertFalse(WHISKERS.equals(5));

        // different Pet -> returns false
        assertFalse(WHISKERS.equals(EXAMPLE_DOG));

        // different name -> returns false
        Pet editedWHISKERS = new PetBuilder(WHISKERS).withName(VALID_NAME_BOB).build();
        assertFalse(WHISKERS.equals(editedWHISKERS));

        // different phone -> returns false
        editedWHISKERS = new PetBuilder(WHISKERS).withPhone(VALID_PHONE_BOB).build();
        assertFalse(WHISKERS.equals(editedWHISKERS));

        // different email -> returns false
        editedWHISKERS = new PetBuilder(WHISKERS).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(WHISKERS.equals(editedWHISKERS));

        // different address -> returns false
        editedWHISKERS = new PetBuilder(WHISKERS).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(WHISKERS.equals(editedWHISKERS));

        // different tags -> returns false
        editedWHISKERS = new PetBuilder(WHISKERS).withTags(VALID_TAG_DOG).build();
        assertFalse(WHISKERS.equals(editedWHISKERS));
    }
}
