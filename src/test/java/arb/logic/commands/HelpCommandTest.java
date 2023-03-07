package arb.logic.commands;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success_with_none_list_type() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, ListType.NONE);
        assertCommandSuccess(new HelpCommand(), ListType.NONE, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_success_with_client_list_type() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, ListType.CLIENT);
        assertCommandSuccess(new HelpCommand(), ListType.CLIENT, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_success_with_project_list_type() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, ListType.PROJECT);
        assertCommandSuccess(new HelpCommand(), ListType.PROJECT, model, expectedCommandResult, expectedModel);
    }

}
