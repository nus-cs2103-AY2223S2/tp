package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RemoveMeetingCommand.MESSAGE_REMOVE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveMeetingCommand}.
 */
public class RemoveMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final RemoveMeetingCommand standardCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
        INDEX_FIRST_MEETING);

    private void populateMeetings() {
        Person samplePerson1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person samplePerson2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Meeting m1 = new Meeting(
            LocalDateTime.of(2023, 03, 14, 15, 30),
            LocalDateTime.of(2023, 03, 14, 16, 30)
        );
        Meeting m2 = new Meeting(
            LocalDateTime.of(2023, 03, 14, 16, 30),
            LocalDateTime.of(2023, 03, 14, 17, 30)
        );

        samplePerson1.getMeetings().add(m1);
        samplePerson2.getMeetings().add(m2);
        samplePerson2.getMeetings().add(m1);
    }

    @Test
    public void validMeetingIndexTest() throws CommandException {
        populateMeetings();

        Person samplePerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemoveMeetingCommand sampleRemoveMeetingCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
            INDEX_FIRST_MEETING);

        CommandResult actualSuccessMessage = sampleRemoveMeetingCommand.execute(model);
        assertEquals(String.format(MESSAGE_REMOVE_SUCCESS, samplePerson),
            actualSuccessMessage.getFeedbackToUser());
    }

    @Test
    public void invalidPersonIndexTest() {
        populateMeetings();

        int sampleIndex = 20;
        RemoveMeetingCommand sampleRemoveMeetingCommand = new RemoveMeetingCommand(Index.fromZeroBased(sampleIndex),
            INDEX_FIRST_MEETING);

        assertThrows(CommandException.class, () -> sampleRemoveMeetingCommand.execute(model));
    }

    @Test
    public void invalidMeetingIndexTest() {
        populateMeetings();

        int sampleIndex = 20;
        RemoveMeetingCommand sampleRemoveMeetingCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON,
            Index.fromZeroBased(sampleIndex));

        assertThrows(CommandException.class, () -> sampleRemoveMeetingCommand.execute(model));
    }

    @Test
    public void equals() {
        populateMeetings();

        // same values -> returns true
        RemoveMeetingCommand commandWithSameValues = new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveMeetingCommand(INDEX_SECOND_PERSON, INDEX_FIRST_MEETING)));

        // different meeting -> returns false
        assertFalse(standardCommand.equals(new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
    }
}
