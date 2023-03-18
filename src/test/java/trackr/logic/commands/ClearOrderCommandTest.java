package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.OrderList;
import trackr.model.UserPrefs;

public class ClearOrderCommandTest {

    @Test
    public void execute_emptyOrderList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearOrderCommand(), model, ClearOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyOrderList_success() {
        Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
        expectedModel.setOrderList(new OrderList());

        assertCommandSuccess(new ClearOrderCommand(), model, ClearOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
