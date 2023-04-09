package trackr.logic.commands.order;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showOrderAtIndex;
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
 * Contains integration tests (interaction with the Model) and unit tests for ListOrderCommand.
 */
public class ListOrderCommandTest {

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
    public void execute_orderListIsNotFiltered_showsSameList() throws ParseException {
        assertCommandSuccess(new ListOrderCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.ORDER.toString().toLowerCase()),
                expectedModel);
    }

    @Test
    public void execute_taskListIsFiltered_showsEverything() throws ParseException {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);
        assertCommandSuccess(new ListOrderCommand(),
                model,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.ORDER.toString().toLowerCase()),
                expectedModel);
    }
}
