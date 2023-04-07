package fasttrack.logic.commands.general;

import static fasttrack.logic.commands.general.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.ui.ScreenType;

public class ExitCommandTest {
    private Model dataModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ScreenType.EXPENSE_SCREEN);
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(exitCommand.execute(dataModel), expectedCommandResult);
    }
}
