package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.QuickstartCommand.MESSAGE_QUICKSTART_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class QuickstartCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_quickstart_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_QUICKSTART_ACKNOWLEDGEMENT,
                false, false, true);
        assertCommandSuccess(new QuickstartCommand(), model, expectedCommandResult, expectedModel);
    }
}
