package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.logic.commands.supplier.AddSupplierCommand;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.person.Supplier;
import trackr.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddSupplierCommand}.
 */
public class AddSupplierCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
    }

    @Test
    public void execute_newSupplier_success() {
        Supplier validSupplier = new SupplierBuilder().build();

        Model expectedModel = new ModelManager(model.getSupplierList(),
                model.getTaskList(), model.getOrderList(), new UserPrefs());
        expectedModel.addItem(validSupplier, ModelEnum.SUPPLIER);

        assertCommandSuccess(new AddSupplierCommand(validSupplier), model,
                String.format(AddSupplierCommand.MESSAGE_SUCCESS,
                        ModelEnum.SUPPLIER, validSupplier),
                expectedModel);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier supplierInList = model.getSupplierList().getItemList().get(0);
        assertCommandFailure(new AddSupplierCommand(supplierInList), model,
                String.format(AddSupplierCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.SUPPLIER,
                        ModelEnum.SUPPLIER));
    }

}
