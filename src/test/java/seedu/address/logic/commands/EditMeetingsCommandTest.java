package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingsCommand.
 */
public class EditMeetingsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(INDEX_FIRST_MEETING, descriptor);

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getMeetingsList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getMeetingsList().size());
        Meeting lastMeeting = model.getMeetingsList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withTitle(VALID_MEETING_TITLE).withDescription(VALID_MEETING_DESCRIPTION)
                .withLocation(VALID_MEETING_LOCATION).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_MEETING_TITLE)
                .withDescription(VALID_MEETING_DESCRIPTION).withLocation(VALID_MEETING_LOCATION).build();
        EditMeetingsCommand editedCommand = new EditMeetingsCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingsCommand editMeetingCommand = new EditMeetingsCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptor());
        Meeting editedMeeting = model.getMeetingsList().get(INDEX_FIRST_MEETING.getZeroBased());

        String expectedMessage = String.format(EditMeetingsCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getMeetingsList().get(INDEX_FIRST_MEETING.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(INDEX_SECOND_MEETING, descriptor);

        assertCommandFailure(editMeetingsCommand, model, EditMeetingsCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getMeetingsList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_MEETING_TITLE).build();
        EditMeetingsCommand editMeetingsCommand = new EditMeetingsCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingsCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingsCommand standardCommand = new EditMeetingsCommand(INDEX_FIRST_MEETING, DESC_MEETING_A);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_MEETING_A);
        EditMeetingsCommand commandWithSameValues = new EditMeetingsCommand(INDEX_FIRST_MEETING, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditMeetingsCommand(INDEX_SECOND_MEETING, DESC_MEETING_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditMeetingsCommand(INDEX_FIRST_MEETING, DESC_MEETING_B));
    }

}
