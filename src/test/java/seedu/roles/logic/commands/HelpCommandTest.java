package seedu.roles.logic.commands;

import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult<String> expectedCommandResult = new CommandResult<>(SHOWING_HELP_MESSAGE, true,
                false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
