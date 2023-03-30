package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.ApplicationModelManager;

public class HelpCommandTest {
    private Model model = new ApplicationModelManager();
    private Model expectedModel = new ApplicationModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}

