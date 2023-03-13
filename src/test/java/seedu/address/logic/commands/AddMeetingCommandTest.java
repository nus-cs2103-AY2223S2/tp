package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddMeetingCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMeetingCommand}.
 */
public class AddMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Meeting meeting = new Meeting();

        assertCommandFailure(new AddMeetingCommand(INDEX_FIRST_PERSON, meeting), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), meeting));
    }

    @Test
    public void equals() {
        final AddMeetingCommand standardCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING_AMY);

        // same values -> returns true
        AddMeetingCommand commandWithSameValues = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING_BOB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddMeetingCommand(INDEX_SECOND_PERSON, VALID_MEETING_AMY)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING_BOB)));
    }
}
