package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_END_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_START_AMY;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        UpdateMeetingCommand.EditMeetingDescriptor descriptorWithSameValues =
            new UpdateMeetingCommand.EditMeetingDescriptor(MEETING_DESC_AMY);
        assertTrue(MEETING_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(MEETING_DESC_AMY.equals(MEETING_DESC_AMY));

        // null -> returns false
        assertFalse(MEETING_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(MEETING_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(MEETING_DESC_AMY.equals(MEETING_DESC_BOB));

        // different description -> returns false
        UpdateMeetingCommand.EditMeetingDescriptor editedAmy =
            new EditMeetingDescriptorBuilder(MEETING_DESC_AMY).withDescription(VALID_MEETING_DESC_BOB).build();
        assertFalse(MEETING_DESC_AMY.equals(editedAmy));

        // different start -> returns false
        LocalDateTime newStart = VALID_MEETING_START_AMY.minusHours(1);
        editedAmy = new EditMeetingDescriptorBuilder(MEETING_DESC_AMY).withStart(newStart).build();
        assertFalse(MEETING_DESC_AMY.equals(editedAmy));

        // different end -> returns false
        LocalDateTime newEnd = VALID_MEETING_END_AMY.plusHours(1);
        editedAmy = new EditMeetingDescriptorBuilder(MEETING_DESC_AMY).withEnd(newEnd).build();
        assertFalse(MEETING_DESC_AMY.equals(editedAmy));
    }
}
