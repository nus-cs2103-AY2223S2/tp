package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.ALICE;
import static seedu.address.testutil.TypicalModules.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getTags().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(ALICE.isSameModule(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedAlice = new ModuleBuilder(ALICE).withType(VALID_TYPE_BOB).withTimeSlot(VALID_TIMESLOT_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameModule(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ModuleBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameModule(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Module editedBob = new ModuleBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameModule(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ModuleBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameModule(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module aliceCopy = new ModuleBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different module -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Module editedAlice = new ModuleBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different type -> returns false
        editedAlice = new ModuleBuilder(ALICE).withType(VALID_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different timeSlot -> returns false
        editedAlice = new ModuleBuilder(ALICE).withTimeSlot(VALID_TIMESLOT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ModuleBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ModuleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
