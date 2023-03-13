package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RemoveMeetingCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveMeetingCommand}.
 */
public class RemoveMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {

        assertCommandFailure(new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(),
                        INDEX_SECOND_PERSON.getOneBased()));
    }

    @Test
    public void equals() {
        final RemoveMeetingCommand standardCommand = new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        // same values -> returns true
        RemoveMeetingCommand commandWithSameValues = new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveMeetingCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemoveMeetingCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
    }
}
