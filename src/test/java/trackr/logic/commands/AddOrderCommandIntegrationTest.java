package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.commands.order.AddOrderCommand;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddOrderCommand}.
 */
public class AddOrderCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();
        Model expectedModel = new ModelManager(model.getSupplierList(),
                model.getTaskList(), getTypicalOrderList(), new UserPrefs());
        expectedModel.addItem(validOrder, ModelEnum.ORDER);

        assertCommandSuccess(new AddOrderCommand(validOrder),
                model,
                String.format(AddOrderCommand.MESSAGE_SUCCESS, ModelEnum.ORDER, validOrder),
                expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Order orderInList = model.getOrderList().getItemList().get(0);
        assertCommandFailure(new AddOrderCommand(orderInList), model,
                String.format(AddOrderCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.ORDER, ModelEnum.ORDER));
    }
}
