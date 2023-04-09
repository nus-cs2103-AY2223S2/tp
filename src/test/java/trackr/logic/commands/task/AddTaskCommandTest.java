package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.ModelEnum;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.item.Item;
import trackr.model.task.Task;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TestUtil.ModelStub;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, ModelEnum.TASK, validTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new AddTaskCommandTest.ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                String.format(AddTaskCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.TASK,
                        ModelEnum.TASK), () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task sortInventory = new TaskBuilder().withTaskName("Sort Inventory").build();
        Task buyEggs = new TaskBuilder().withTaskName("Buy eggs").build();
        AddTaskCommand addSortInventoryCommand = new AddTaskCommand(sortInventory);
        AddTaskCommand addBuyEggsCommand = new AddTaskCommand(buyEggs);

        // same object -> returns true
        assertTrue(addSortInventoryCommand.equals(addSortInventoryCommand));

        // same values -> returns true
        AddTaskCommand addSortInventoryCommandCopy = new AddTaskCommand(sortInventory);
        assertTrue(addSortInventoryCommand.equals(addSortInventoryCommandCopy));

        // different types -> returns false
        assertFalse(addSortInventoryCommand.equals(1));

        // null -> returns false
        assertFalse(addSortInventoryCommand.equals(null));

        // different task -> returns false
        assertFalse(addSortInventoryCommand.equals(addBuyEggsCommand));
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return this.task.isSameItem((Task) item);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return tasksAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            tasksAdded.add((Task) item);
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return new TaskList();
        }
    }

}
