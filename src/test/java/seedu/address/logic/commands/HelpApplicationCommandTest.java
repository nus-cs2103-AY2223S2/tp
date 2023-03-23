package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpApplicationCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;

public class HelpApplicationCommandTest {
    private ApplicationModel model = new ApplicationModelManager();
    private ApplicationModel expectedModel = new ApplicationModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpApplicationCommand(), model, expectedCommandResult, expectedModel);
    }
}

