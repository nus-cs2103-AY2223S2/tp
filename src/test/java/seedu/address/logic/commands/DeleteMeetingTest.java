package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;


import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteMeeting_success() {
        Meeting meetingToDelete = model.getMeetingsList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeMeeting(meetingToDelete);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMeetingIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getMeetingsList().size() + 1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        DeleteMeetingCommand deleteSecondMeetingCommand = new DeleteMeetingCommand(INDEX_SECOND_MEETING);

        // the same object should return true
        assertTrue(deleteFirstMeetingCommand.equals(deleteFirstMeetingCommand));

        // command object with same index should return true
        assertTrue(deleteFirstMeetingCommand.equals(new DeleteMeetingCommand(INDEX_FIRST_MEETING)));

        // different object type should return false
        assertFalse(deleteFirstMeetingCommand.equals(1));

        // null returns false
        assertFalse(deleteFirstMeetingCommand.equals(null));

        // different person should return false
        assertFalse(deleteFirstMeetingCommand.equals(deleteSecondMeetingCommand));
    }
}
