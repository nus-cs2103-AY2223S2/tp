package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCustomerCommand}.
 */
public class ViewCustomerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToView = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(INDEX_FIRST_CUSTOMER);

        String expectedMessage = String.format(ViewCustomerCommand.MESSAGE_VIEW_CUSTOMER_SUCCESS, customerToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, 0);

        assertCommandSuccess(viewCustomerCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(outOfBoundIndex);

        assertCommandFailure(viewCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(outOfBoundIndex);

        assertCommandFailure(viewCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCustomerCommand viewFirstCommand = new ViewCustomerCommand(INDEX_FIRST_CUSTOMER);
        ViewCustomerCommand viewSecondCommand = new ViewCustomerCommand(INDEX_SECOND_CUSTOMER);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCustomerCommand viewFirstCommandCopy = new ViewCustomerCommand(INDEX_FIRST_CUSTOMER);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
