package arb.logic.commands;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exitSuccess_withCurrentListTypeClient() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false,
                ListType.NONE);
        assertCommandSuccess(new ExitCommand(), ListType.CLIENT, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_exitSuccess_withCurrentListTypeProject() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false,
                ListType.NONE);
        assertCommandSuccess(new ExitCommand(), ListType.PROJECT, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_exitSuccess_withCurrentListTypeTag() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false,
                ListType.NONE);
        assertCommandSuccess(new ExitCommand(), ListType.TAG, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

}
