package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_SPORTS_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_CARNIVAL);
        assertTrue(DESC_CARNIVAL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CARNIVAL.equals(DESC_CARNIVAL));

        // null -> returns false
        assertFalse(DESC_CARNIVAL.equals(null));

        // different types -> returns false
        assertFalse(DESC_CARNIVAL.equals(5));

        // different values -> returns false
        assertFalse(DESC_CARNIVAL.equals(DESC_SPORTS_DAY));

        // different event name -> returns false
        EditEventDescriptor editedCarnival = new EditEventDescriptorBuilder(DESC_CARNIVAL)
                .withEventName(VALID_EVENT_NAME_SPORTS_DAY).build();
        assertFalse(DESC_CARNIVAL.equals(editedCarnival));

        // different start datetime -> returns false
        editedCarnival = new EditEventDescriptorBuilder(DESC_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_SPORTS_DAY).build();
        assertFalse(DESC_CARNIVAL.equals(editedCarnival));

        // different end datetime -> returns false
        editedCarnival = new EditEventDescriptorBuilder(DESC_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_SPORTS_DAY).build();
        assertFalse(DESC_CARNIVAL.equals(editedCarnival));
    }
}
