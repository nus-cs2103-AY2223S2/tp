package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.TypicalCustomers.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.testutil.CustomerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newCustomer_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCommand(validCustomer), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        Customer customerInList = model.getAddressBook().getCustomerList().get(0);
        assertCommandFailure(new AddCommand(customerInList), model, AddCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
