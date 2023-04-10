package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_CUSTOMERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.CustomerTypePredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCustomerCommand.
 */
public class ListCustomerCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListCustomerCommand listCommand = new ListCustomerCommand();
        ListCustomerCommand listSortPointsCommand = new ListCustomerCommand(
                Customer.SORT_POINTS, PREDICATE_SHOW_ALL_CUSTOMERS);
        ListCustomerCommand listSortNameIndividualCommand = new ListCustomerCommand(
                Customer.SORT_NAME, new CustomerTypePredicate(CustomerType.INDIVIDUAL));

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same comparator and predicate -> returns true
        ListCustomerCommand listCommandCopy = new ListCustomerCommand();
        assertTrue(listCommand.equals(listCommandCopy));

        ListCustomerCommand listSortNameIndividualCommandCopy = new ListCustomerCommand(
                Customer.SORT_NAME, new CustomerTypePredicate(CustomerType.INDIVIDUAL));
        assertTrue(listSortNameIndividualCommand.equals(listSortNameIndividualCommandCopy));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));

        // different comparator -> returns false
        assertFalse(listCommand.equals(listSortPointsCommand));

        // different predicate -> returns false
        assertFalse(listCommand.equals(listSortNameIndividualCommand));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(
                ListCustomerCommand.MESSAGE_SUCCESS, LIST_CUSTOMERS_ONLY);
        assertCommandSuccess(new ListCustomerCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCustomerAtIndex(model, INDEX_FIRST);
        CommandResult expectedCommandResult = new CommandResult(
                ListCustomerCommand.MESSAGE_SUCCESS, LIST_CUSTOMERS_ONLY);
        assertCommandSuccess(new ListCustomerCommand(), model, expectedCommandResult, expectedModel);
    }
}
