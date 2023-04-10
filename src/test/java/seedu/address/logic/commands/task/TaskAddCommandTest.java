package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;


class TaskAddCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(null, Index.fromOneBased(1)));
    }

    @Test
    public void getTaskTank_invalidTask_throwsCommandException() {
        Model model = new ModelManager();
        //empty model, 1 is invalid
        assertThrows(CommandException.class, () -> new TaskAddCommand(new TaskStub(), Index.fromOneBased(1))
                .getTaskTank(Index.fromOneBased(1), model));
    }

    @Test
    public void executeAddTask_duplicateTask_throwsCommandException() {
        //try adding a task with no tank and priority attached to model with copy of it
        Model model = new ModelManager();
        Task toAdd = new TaskStub();
        model.addTask(toAdd);
        TaskAddCommand cmd = new TaskAddCommand(toAdd, null);
        assertThrows(CommandException.class, TaskAddCommand.MESSAGE_DUPLICATE_TASK, () -> cmd.execute(model));
    }

    @Test
    public void executeAddTask_taskStub_success() throws Exception {
        Model model = new ModelManager();
        Task toAdd = new TaskStub();
        CommandResult commandResult = new TaskAddCommand(toAdd, null).execute(model);
        assertEquals(String.format(TaskAddCommand.MESSAGE_SUCCESS, toAdd), commandResult.getFeedbackToUser());
        assertTrue(model.hasTask(toAdd));
    }

    private class TaskStub extends Task {
        /**
         * Constructor for a Task
         */
        public TaskStub() {
            super(new Description("description"), null, null);
        }
    }
}
