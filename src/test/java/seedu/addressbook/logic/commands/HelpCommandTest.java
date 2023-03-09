package seedu.addressbook.logic.commands;

import static seedu.addressbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.addressbook.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.addressbook.model.FitBookModel;
import seedu.addressbook.model.FitBookModelManager;

public class HelpCommandTest {
    private FitBookModel model = new FitBookModelManager();
    private FitBookModel expectedFitBookModel = new FitBookModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedFitBookModel);
    }
}
