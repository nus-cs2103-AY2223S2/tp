package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalSuppliers.getTypicalAddressBook;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackr.model.Model;
import trackr.model.ModelManager;
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
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Supplier validPerson = new SupplierBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.addSupplier(validPerson);

        assertCommandSuccess(new AddSupplierCommand(validPerson), model,
                String.format(AddSupplierCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Supplier personInList = model.getAddressBook().getSupplierList().get(0);
        assertCommandFailure(new AddSupplierCommand(personInList), model, AddSupplierCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }

}
