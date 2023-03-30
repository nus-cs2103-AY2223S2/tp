package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.ApplicationModelManager;

public class ExitCommandTest {
    private Model model = new ApplicationModelManager();
    private Model expectedModel = new ApplicationModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}
