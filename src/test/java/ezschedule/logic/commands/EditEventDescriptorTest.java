package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.EDIT_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.EDIT_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_END_TIME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(EDIT_DESC_A);
        assertTrue(EDIT_DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_DESC_A.equals(EDIT_DESC_A));

        // null -> returns false
        assertFalse(EDIT_DESC_A.equals(null));

        // different types -> returns false
        assertFalse(EDIT_DESC_A.equals(5));

        // different values -> returns false
        assertFalse(EDIT_DESC_A.equals(EDIT_DESC_B));

        // different name -> returns false
        EditEventDescriptor editedA = new EditEventDescriptorBuilder(EDIT_DESC_A).withName(VALID_NAME_B).build();
        assertFalse(EDIT_DESC_A.equals(editedA));

        // different date -> returns false
        editedA = new EditEventDescriptorBuilder(EDIT_DESC_A).withDate(VALID_DATE_B).build();
        assertFalse(EDIT_DESC_A.equals(editedA));

        // different start time -> returns false
        editedA = new EditEventDescriptorBuilder(EDIT_DESC_A).withStartTime(VALID_START_TIME_B).build();
        assertFalse(CommandTestUtil.EDIT_DESC_A.equals(editedA));

        // different end time -> returns false
        editedA = new EditEventDescriptorBuilder(EDIT_DESC_A).withEndTime(VALID_END_TIME_B).build();
        assertFalse(EDIT_DESC_A.equals(editedA));
    }
}
