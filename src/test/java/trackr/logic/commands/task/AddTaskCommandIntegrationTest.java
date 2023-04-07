package trackr.logic.commands.task;


import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.task.Task;
import trackr.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTaskCommand}.
 */
public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() throws ParseException {
        Task validTask = new TaskBuilder().build();
        Model expectedModel = new ModelManager(model.getSupplierList(),
                model.getTaskList(), getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
        expectedModel.addItem(validTask, ModelEnum.TASK);

        assertCommandSuccess(new AddTaskCommand(validTask), model,
                String.format(AddTaskCommand.MESSAGE_SUCCESS, ModelEnum.TASK, validTask),
                expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskList().getItemList().get(0);
        assertCommandFailure(new AddTaskCommand(taskInList), model,
                String.format(AddTaskCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.TASK, ModelEnum.TASK));
    }

}
