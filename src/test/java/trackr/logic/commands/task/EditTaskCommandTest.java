package trackr.logic.commands.task;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showTaskAtIndex;
import static trackr.logic.commands.EditItemCommand.MESSAGE_DUPLICATE_ITEM;
import static trackr.logic.commands.EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.supplier.ClearSupplierCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Menu;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.OrderList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.task.Task;
import trackr.model.task.TaskDescriptor;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TaskDescriptorBuilder;

//@@author hmuumyatmoe-reused
public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void execute_allFieldsSpecifiedUnfilteredTaskList_success() throws ParseException {
        Task editedTask = new TaskBuilder().build();
        TaskDescriptor descriptor = new TaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.TASK.toString().toLowerCase(),
                editedTask);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setItem(model.getFilteredTaskList().get(0), editedTask, ModelEnum.TASK);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredTaskList_success() throws ParseException {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        taskInList.withTaskName(VALID_TASK_NAME_SORT_INVENTORY);
        taskInList.withTaskDeadline(VALID_TASK_DEADLINE_2100);
        taskInList.withTaskStatus(VALID_TASK_STATUS_DONE);
        Task editedTask = taskInList.build();

        TaskDescriptor descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.TASK.toString().toLowerCase(),
                editedTask);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setItem(lastTask, editedTask, ModelEnum.TASK);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredTaskList_success() throws ParseException {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT, new TaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.TASK.toString().toLowerCase(),
                editedTask);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredTaskList_success() throws ParseException {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT,
                new TaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build());

        String expectedMessage = String.format(MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.TASK.toString().toLowerCase(),
                editedTask);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setItem(model.getFilteredTaskList().get(0), editedTask, ModelEnum.TASK);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredTaskList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        TaskDescriptor descriptor = new TaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editTaskCommand,
                model,
                String.format(MESSAGE_DUPLICATE_ITEM, ModelEnum.TASK.toString().toLowerCase()));
    }

    @Test
    public void execute_duplicateTaskFilteredTaskList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        // edit task in filtered task list into a duplicate in task list
        Task taskInList = model.getTaskList().getItemList().get(INDEX_SECOND_OBJECT.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT,
                new TaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editTaskCommand,
                model,
                String.format(MESSAGE_DUPLICATE_ITEM, ModelEnum.TASK.toString().toLowerCase()));
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredTaskList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TaskDescriptor descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered task list where index is larger than size of filtered task list,
     * but smaller than size of task list
     */
    @Test
    public void execute_invalidTaskIndexFilteredTaskList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getItemList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex,
                new TaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build());

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardTaskCommand =
                new EditTaskCommand(INDEX_FIRST_OBJECT, DESC_SORT_INVENTORY);

        // same values -> returns true
        TaskDescriptor copyTaskDescriptor = new TaskDescriptor(DESC_SORT_INVENTORY);
        EditTaskCommand commandWithSameValues =
                new EditTaskCommand(INDEX_FIRST_OBJECT, copyTaskDescriptor);
        assertTrue(standardTaskCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardTaskCommand.equals(standardTaskCommand));

        // null -> returns false
        assertFalse(standardTaskCommand.equals(null));

        // different types -> returns false
        assertFalse(standardTaskCommand.equals(new ClearSupplierCommand()));

        // different index -> returns false
        assertFalse(standardTaskCommand.equals(
                new EditTaskCommand(INDEX_SECOND_OBJECT, DESC_SORT_INVENTORY)));

        // different descriptor -> returns false
        assertFalse(standardTaskCommand.equals(
                new EditTaskCommand(INDEX_FIRST_OBJECT, DESC_BUY_FLOUR)));
    }
    //@@author
}
