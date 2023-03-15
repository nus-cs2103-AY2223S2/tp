package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS1101S);
        assertTrue(DESC_CS1101S.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS1101S.equals(DESC_CS1101S));

        // null -> returns false
        assertFalse(DESC_CS1101S.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS1101S.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS1101S.equals(DESC_MA2002));

        // different name -> returns false
        EditModuleDescriptor editedAmy = new EditModuleDescriptorBuilder(DESC_CS1101S)
                .withCode(VALID_CODE_MA2002).build();
        assertFalse(DESC_CS1101S.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS1101S).withCredit(VALID_CREDIT_MA2002).build();
        assertFalse(DESC_CS1101S.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS1101S).withSemYear(VALID_SEMYEAR_MA2002).build();
        assertFalse(DESC_CS1101S.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS1101S).withGrade(VALID_GRADE_MA2002).build();
        assertFalse(DESC_CS1101S.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS1101S).withTags(VALID_TAG_CS1101S).build();
        assertFalse(DESC_CS1101S.equals(editedAmy));
    }
}
