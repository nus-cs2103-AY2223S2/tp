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
    public void execute_exit_success_with_none_list_type() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ListType.NONE);
        assertCommandSuccess(new ExitCommand(), ListType.NONE, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exit_success_with_client_list_type() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ListType.CLIENT);
        assertCommandSuccess(new ExitCommand(), ListType.CLIENT, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exit_success_with_project_list_type() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ListType.PROJECT);
        assertCommandSuccess(new ExitCommand(), ListType.PROJECT, model, expectedCommandResult, expectedModel);
    }

}
