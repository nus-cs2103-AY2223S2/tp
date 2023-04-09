package trackr.logic.commands.task;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showTaskAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.commands.ListItemCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTaskCommand.
 */
public class ListTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
        expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                getTypicalMenu(), model.getOrderList(), new UserPrefs());
    }

    @Test
    public void execute_taskListIsNotFiltered_showsSameList() throws ParseException {
        assertCommandSuccess(new ListTaskCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.TASK.toString().toLowerCase()),
                expectedModel);
    }

    @Test
    public void execute_taskListIsFiltered_showsEverything() throws ParseException {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);
        assertCommandSuccess(new ListTaskCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.TASK.toString().toLowerCase()),
                expectedModel);
    }
}
