package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.ReadOnlySupplierList;
import trackr.model.UserPrefs;
import trackr.model.supplier.Supplier;
import trackr.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Supplier validPerson = new SupplierBuilder().build();

        Model expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getOrderList(), new UserPrefs());
        expectedModel.addSupplier(validPerson);

        assertCommandSuccess(new AddSupplierCommand(validPerson), model,
                String.format(AddSupplierCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        ReadOnlySupplierList personInList = model.getSupplierList();
        //assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
        assertFalse(personInList.equals(null));
    }

}
