package fasttrack.logic.commands;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fasttrack.logic.commands.general.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.general.ExitCommand;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.ui.ScreenType;

public class ExitCommandTest {
    private Model dataModel = new ModelManager();
    private Model expectedDataModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT, ScreenType.EXPENSE_SCREEN);
        assertCommandSuccess(new ExitCommand(), dataModel, expectedCommandResult, expectedDataModel);
    }
}
