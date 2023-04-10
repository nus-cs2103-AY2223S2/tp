package seedu.vms.logic.commands.basic;

import static seedu.vms.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.vms.logic.commands.basic.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.CommandMessage;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandMessage expectedCommandResult = new CommandMessage(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
