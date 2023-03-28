package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;

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
        ListCustomerCommand listSortOrderCommand = new ListCustomerCommand(Customer.SORT_POINTS);

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same comparator -> returns true
        ListCustomerCommand listCommandCopy = new ListCustomerCommand();
        assertTrue(listCommand.equals(listCommandCopy));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));

        // different comparator -> returns false
        assertFalse(listCommand.equals(listSortOrderCommand));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCustomerCommand(null), model, ListCustomerCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCustomerAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListCustomerCommand(null), model, ListCustomerCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
