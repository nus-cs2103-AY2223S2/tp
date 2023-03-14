package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_BOB;
import static seedu.address.testutil.Assert.assertThrows;
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

public class AddMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final AddMeetingCommand standardCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING_AMY);

    @Test
    public void nullMeetingTest() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void addMeetingSuccessTest() throws Exception {
        final Meeting meeting = new Meeting(
            LocalDateTime.of(2023, 03, 14, 15, 30),
            LocalDateTime.of(2023, 03, 14, 16, 30)
        );

        Person samplePerson = model.getFilteredPersonList().get(0);

        AddMeetingCommand sampleAddMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);
        CommandResult actualSuccessMessage = sampleAddMeetingCommand.execute(model);

        assertEquals(String.format(MESSAGE_ADD_MEETING_SUCCESS, samplePerson),
            actualSuccessMessage.getFeedbackToUser());
    }

    @Test
    public void outOfRangeIndexTest() throws Exception {
        final Meeting meeting = new Meeting(
            LocalDateTime.of(2023, 03, 14, 15, 30),
            LocalDateTime.of(2023, 03, 14, 16, 30)
        );

        AddMeetingCommand sampleAddMeetingCommand = new AddMeetingCommand(
            Index.fromZeroBased(20), meeting
        );

        assertThrows(CommandException.class, () -> sampleAddMeetingCommand.execute(model));
    }

    @Test
    public void clashInMeetingsTest() throws Exception {
        Meeting sampleMeeting = new Meeting(
            LocalDateTime.of(2023, 03, 14, 16, 00),
            LocalDateTime.of(2023, 03, 14, 17, 30)
        );


        assertThrows(CommandException.class, () ->
            new AddMeetingCommand(INDEX_FIRST_PERSON, sampleMeeting)
                .execute(model));
    }

    @Test
    public void equalsTest() {
        // same values -> returns true
        AddMeetingCommand commandWithSameValues = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
    }

    @Test
    public void notEqualsTest() {
        // null object -> returns false
        assertFalse(standardCommand.equals(null));

        // Different command -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // Different inputs -> return false
        assertFalse(standardCommand.equals(new AddMeetingCommand(INDEX_SECOND_PERSON, VALID_MEETING_BOB)));
    }

}
