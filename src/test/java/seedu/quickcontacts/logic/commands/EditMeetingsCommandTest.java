package seedu.quickcontacts.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.quickcontacts.commons.core.Messages;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.EditMeetingDescriptorBuilder;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.TypicalIndexes;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingsCommand.
 */
public class EditMeetingsCommandTest {

    private final Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(
                TypicalIndexes.INDEX_FIRST_MEETING, descriptor);

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new QuickBook(model.getQuickBook()), new UserPrefs());
        expectedModel.setMeeting(model.getMeetingsList().get(0), editedMeeting);

        CommandTestUtil.assertCommandSuccess(editMeetingsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getMeetingsList().size());
        Meeting lastMeeting = model.getMeetingsList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withTitle(CommandTestUtil.VALID_MEETING_TITLE)
                .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION)
                .withLocation(CommandTestUtil.VALID_MEETING_LOCATION).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withTitle(CommandTestUtil.VALID_MEETING_TITLE)
                .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION)
                .withLocation(CommandTestUtil.VALID_MEETING_LOCATION).build();
        EditMeetingsCommand editedCommand = new EditMeetingsCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new QuickBook(model.getQuickBook()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        CommandTestUtil.assertCommandSuccess(editedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingsCommand editMeetingCommand = new EditMeetingsCommand(TypicalIndexes.INDEX_FIRST_MEETING,
                new EditMeetingDescriptor());
        Meeting editedMeeting = model.getMeetingsList().get(TypicalIndexes.INDEX_FIRST_MEETING.getZeroBased());

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new QuickBook(model.getQuickBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getMeetingsList().get(TypicalIndexes.INDEX_FIRST_MEETING.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(
                TypicalIndexes.INDEX_SECOND_MEETING, descriptor);

        CommandTestUtil.assertCommandFailure(editMeetingsCommand, model, EditMeetingsCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getMeetingsList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withTitle(CommandTestUtil.VALID_MEETING_TITLE).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editMeetingsCommand, model,
                Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingsCommand standardCommand = new EditMeetingsCommand(
                TypicalIndexes.INDEX_FIRST_MEETING, CommandTestUtil.DESC_MEETING_A);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(CommandTestUtil.DESC_MEETING_A);
        EditMeetingsCommand commandWithSameValues = new EditMeetingsCommand(
                TypicalIndexes.INDEX_FIRST_MEETING, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        Assertions.assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditMeetingsCommand(
                TypicalIndexes.INDEX_SECOND_MEETING, CommandTestUtil.DESC_MEETING_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditMeetingsCommand(
                TypicalIndexes.INDEX_FIRST_MEETING, CommandTestUtil.DESC_MEETING_B));
    }

}
