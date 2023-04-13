package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private TaskBookModel taskBookmodel = new TaskBookModelManager();
    private Model expectedModel = new ModelManager();
    private TaskBookModel expectedTaskBookModel = new TaskBookModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, taskBookmodel, expectedCommandResult,
            expectedModel, expectedTaskBookModel);
    }
}
