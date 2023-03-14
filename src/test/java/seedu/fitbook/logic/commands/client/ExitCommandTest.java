package seedu.fitbook.logic.commands.client;

import static seedu.fitbook.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.ExitCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;

public class ExitCommandTest {
    private FitBookModel model = new FitBookModelManager();
    private FitBookModel expectedFitBookModel = new FitBookModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        CommandTestUtil.assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedFitBookModel);
    }
}
