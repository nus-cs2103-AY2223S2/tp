package fasttrack.logic.commands.general;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fasttrack.logic.commands.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.ui.ScreenType;

public class HelpCommandTest {
    private Model dataModel = new ModelManager();
    private Model expectedDataModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false,
                ScreenType.EXPENSE_SCREEN);
        assertCommandSuccess(new HelpCommand(), dataModel, expectedCommandResult, expectedDataModel);
    }
}
