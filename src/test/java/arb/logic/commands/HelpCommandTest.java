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
    public void execute_helpSuccess_withCurrentListTypeClient() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false,
                ListType.NONE);
        assertCommandSuccess(new HelpCommand(), ListType.CLIENT, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_helpSuccess_withCurrentListTypeProject() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false,
                ListType.NONE);
        assertCommandSuccess(new HelpCommand(), ListType.PROJECT, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_helpSuccess_withCurrentListTypeTag() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false,
                ListType.NONE);
        assertCommandSuccess(new HelpCommand(), ListType.TAG, ListType.NONE, model, expectedCommandResult,
                expectedModel);
    }

}
