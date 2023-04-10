package seedu.fitbook.logic.commands.client;

import static seedu.fitbook.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.HelpCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;

public class HelpCommandTest {
    private FitBookModel model = new FitBookModelManager();
    private FitBookModel expectedFitBookModel = new FitBookModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_HELP_MESSAGE,
                        null, true, false, false, false, false);
        CommandTestUtil.assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedFitBookModel);
    }
}
