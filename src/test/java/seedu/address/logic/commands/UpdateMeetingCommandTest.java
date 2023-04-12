package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UpdateMeetingCommand.MESSAGE_UPDATE_MEETING_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class UpdateMeetingCommandTest {
    private final String desc = "Edited";
    private final LocalDateTime start = LocalDateTime.of(2023, 3, 25, 12, 0);
    private final LocalDateTime end = LocalDateTime.of(2023, 3, 25, 15, 0);
    private final Meeting editedMeeting = new Meeting(desc, start, end);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        UpdateMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        UpdateMeetingCommand updateMeetingCommand =
            new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING, descriptor);

        Person originalPerson = model.getFilteredPersonList().get(0);
        Person expectedPerson = new PersonBuilder(originalPerson).build();
        expectedPerson.setMeeting(0, editedMeeting);

        // System should call toString() on the expectedPerson instead of the editedPerson
        String expectedMessage = String.format(MESSAGE_UPDATE_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        assertCommandSuccess(updateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        Person editedPerson = new PersonBuilder(lastPerson).build();
        editedPerson.getMeeting(0).setDescription("ChangeDesc");

        UpdateMeetingCommand.EditMeetingDescriptor descriptor =
            new EditMeetingDescriptorBuilder().withDescription("ChangeDesc").build();
        UpdateMeetingCommand updateMeetingCommand =
            new UpdateMeetingCommand(indexLastPerson, INDEX_FIRST_MEETING, descriptor);
        Meeting newEditedMeeting = new Meeting("ChangeDesc", start, end);
        String expectedMessage = String.format(UpdateMeetingCommand.MESSAGE_UPDATE_MEETING_SUCCESS, newEditedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(updateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateMeetingCommand updateMeetingCommand = new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING,
            new UpdateMeetingCommand.EditMeetingDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Meeting newEditedMeeting = editedPerson.getMeeting(0);
        String expectedMessage = String.format(UpdateMeetingCommand.MESSAGE_UPDATE_MEETING_SUCCESS, newEditedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(updateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).build();
        LocalDateTime newStart = LocalDateTime.of(2023, 3, 25, 1, 0);
        editedPerson.getMeeting(0).setStart(newStart);
        UpdateMeetingCommand updateMeetingCommand = new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING,
            new EditMeetingDescriptorBuilder().withStart(newStart).build());
        Meeting newEditedMeeting = new Meeting(desc, newStart, end);
        String expectedMessage = String.format(UpdateMeetingCommand.MESSAGE_UPDATE_MEETING_SUCCESS, newEditedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(updateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UpdateMeetingCommand.EditMeetingDescriptor descriptor =
            new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_BOB).build();
        UpdateMeetingCommand updateMeetingCommand =
            new UpdateMeetingCommand(outOfBoundIndex, INDEX_FIRST_MEETING, descriptor);

        assertCommandFailure(updateMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UpdateMeetingCommand updateMeetingCommand = new UpdateMeetingCommand(outOfBoundIndex, INDEX_FIRST_MEETING,
            new EditMeetingDescriptorBuilder().withDescription(VALID_MEETING_DESC_BOB).build());

        assertCommandFailure(updateMeetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateMeetingCommand standardCommand =
            new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING, MEETING_DESC_AMY);

        // same values -> returns true
        UpdateMeetingCommand.EditMeetingDescriptor copyDescriptor =
            new UpdateMeetingCommand.EditMeetingDescriptor(MEETING_DESC_AMY);
        UpdateMeetingCommand commandWithSameValues =
            new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
            new UpdateMeetingCommand(INDEX_SECOND_PERSON, INDEX_FIRST_MEETING, MEETING_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
            new UpdateMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING, MEETING_DESC_BOB)));
    }
}
