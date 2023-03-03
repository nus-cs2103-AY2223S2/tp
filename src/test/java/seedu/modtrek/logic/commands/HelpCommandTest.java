package seedu.modtrek.logic.commands;

import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.HelpCommand;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
