package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalDailyPlans;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.Task;
import seedu.task.testutil.SimpleTaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalDailyPlans());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new SimpleTaskBuilder().build();
        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());
        expectedModel.addTask(validTask);
        assertCommandSuccess(new AddCommand(validTask), model,
                String.format(AddCommand.MESSAGE_SUCCESS, "1. " + validTask + "\n"), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        ArrayList<Task> taskInList = new ArrayList<>();
        taskInList.add(model.getTaskBook().getTaskList().get(0));
        assertCommandFailure(new AddCommand(taskInList), model, AddCommand.MESSAGE_DUPLICATE_TASK);
    }

}
