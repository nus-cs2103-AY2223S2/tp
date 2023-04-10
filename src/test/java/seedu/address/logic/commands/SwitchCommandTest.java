package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SwitchCommand.MESSAGE_SWITCH_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SwitchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switch_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SWITCH_SUCCESS, true);
        assertCommandSuccess(new SwitchCommand(), model, expectedCommandResult, expectedModel);
    }
}
