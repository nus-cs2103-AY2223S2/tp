package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.SortCommand.SORT_SUCCESS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;

public class SortCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_sortEmptyTaskBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortCommand(), model, SORT_SUCCESS_MESSAGE, expectedModel);
    }

    @Test
    public void execute_sort_success() {
        CommandResult expectedCommandResult = new CommandResult(SORT_SUCCESS_MESSAGE, false, false);
        assertCommandSuccess(new SortCommand(), model, expectedCommandResult, expectedModel);
    }
}

