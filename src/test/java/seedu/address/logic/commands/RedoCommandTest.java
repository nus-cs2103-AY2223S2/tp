package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;

class RedoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_noRedoableCommand_failure() {
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_NO_REDOABLE_COMMAND);
    }
    @Test
    void execute_undoableCommandPresent_success() {
        model.addPerson(TypicalPersons.IDA);
        try {
            new UndoCommand().execute(model);
        } catch (CommandException e) {
            fail("Unable to execute undo command!");
        }

        assertCommandSuccess(new RedoCommand(), model,
                String.format("Successfully redone command:\nAdd %1$s", TypicalPersons.IDA), model);
    }

}
