package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitSprintCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;

public class ExitSprintCommandTest {
    private ApplicationModel model = new ApplicationModelManager();
    private ApplicationModel expectedModel = new ApplicationModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitSprintCommand(), model, expectedCommandResult, expectedModel);
    }
}
