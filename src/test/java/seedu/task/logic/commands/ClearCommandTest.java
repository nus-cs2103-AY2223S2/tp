package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalDailyPlans;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.TaskBook;
import seedu.task.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTaskBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskBook_success() {
        Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalDailyPlans());
        Model expectedModel = new ModelManager(getTypicalTaskBook(), new UserPrefs(), model.getPlanner());
        expectedModel.setTaskBook(new TaskBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
