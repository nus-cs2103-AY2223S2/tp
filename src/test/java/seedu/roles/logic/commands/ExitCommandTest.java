package seedu.roles.logic.commands;

import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult<String> expectedCommandResult = new CommandResult<>(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
