package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() throws ParseException {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
