package seedu.connectus.logic.commands;

import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.connectus.logic.commands.HelpCommand.SHOWING_HELP_WINDOW;

import org.junit.jupiter.api.Test;

import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_helpWithoutInput_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_WINDOW, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpWithValidInput_success() {
        ClearCommand clearCommand = new ClearCommand();
        CommandResult expectedCommandResult = new CommandResult("Command usage retrieved!\n"
                + clearCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(new HelpCommand(clearCommand.MESSAGE_USAGE), model, expectedCommandResult,
                expectedModel);
    }
}
