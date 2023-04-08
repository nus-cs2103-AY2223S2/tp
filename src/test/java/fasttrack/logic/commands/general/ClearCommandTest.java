package fasttrack.logic.commands.general;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.ui.ScreenType;

public class ClearCommandTest {

    private Model dataModel = new ModelManager();
    private Model expectedDataModel = new ModelManager();

    @Test
    public void execute_clear_success() {
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false,
                ScreenType.EXPENSE_SCREEN);
        assertCommandSuccess(new ClearCommand(), dataModel, expectedCommandResult, expectedDataModel);
    }

}
