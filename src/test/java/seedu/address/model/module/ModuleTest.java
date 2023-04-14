package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3219;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS3219;
import static seedu.address.testutil.TypicalModules.CS3230;

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
        assertTrue(CS3230.isSameModule(CS3230));

        // null -> returns false
        assertFalse(CS3230.isSameModule(null));

        // same name, same tag, all other attributes different -> returns true
        Module editedAlice = new ModuleBuilder(CS3230).withResource(VALID_RESOURCE_CS3219)
                .withTimeSlot(VALID_TIMESLOT_CS3219)
                .withAddress(VALID_ADDRESS_CS3219).build();
        assertTrue(CS3230.isSameModule(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ModuleBuilder(CS3230).withName(VALID_NAME_CS3219).build();
        assertFalse(CS3230.isSameModule(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Module editedBob = new ModuleBuilder(CS3219).withName(VALID_NAME_CS3219.toLowerCase()).build();
        assertFalse(CS3219.isSameModule(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_CS3219 + " ";
        editedBob = new ModuleBuilder(CS3219).withName(nameWithTrailingSpaces).build();
        assertFalse(CS3219.isSameModule(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module aliceCopy = new ModuleBuilder(CS3230).build();
        assertTrue(CS3230.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CS3230.equals(CS3230));

        // null -> returns false
        assertFalse(CS3230.equals(null));

        // different type -> returns false
        assertFalse(CS3230.equals(5));

        // different module -> returns false
        assertFalse(CS3230.equals(CS3219));

        // different name -> returns false
        Module editedAlice = new ModuleBuilder(CS3230).withName(VALID_NAME_CS3219).build();
        assertFalse(CS3230.equals(editedAlice));

        // different type -> returns false
        editedAlice = new ModuleBuilder(CS3230).withTags(VALID_TAG_CS3219).build();
        assertFalse(CS3230.equals(editedAlice));

        // different timeSlot -> returns false
        editedAlice = new ModuleBuilder(CS3230).withTimeSlot(VALID_TIMESLOT_CS3219).build();
        assertFalse(CS3230.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ModuleBuilder(CS3230).withAddress(VALID_ADDRESS_CS3219).build();
        assertFalse(CS3230.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ModuleBuilder(CS3230).withTags(VALID_TAG_CS3219).build();
        assertFalse(CS3230.equals(editedAlice));
    }
}
