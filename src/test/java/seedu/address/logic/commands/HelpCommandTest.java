package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(
                SHOWING_HELP_MESSAGE, true, false, false, false, false, false, false, false, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand helpCommand = new HelpCommand();

        // same object -> returns true
        assertTrue(helpCommand.equals(helpCommand));

        // different types -> returns false
        assertFalse(helpCommand.equals(1));

        // null -> returns false
        assertFalse(helpCommand.equals(null));

        // different HelpCommand -> returns true
        assertTrue(helpCommand.equals(new HelpCommand()));
    }
}
