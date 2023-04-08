package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

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
        Customer customerToView = model.getFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(INDEX_FIRST);

        String expectedMessage = String.format(ViewCustomerCommand.MESSAGE_VIEW_CUSTOMER_SUCCESS, customerToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, LIST_AND_SHOW_CUSTOMER);

        assertCommandSuccess(viewCustomerCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(outOfBoundIndex);

        assertCommandFailure(viewCustomerCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, ViewCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        ViewCustomerCommand viewCustomerCommand = new ViewCustomerCommand(outOfBoundIndex);

        assertCommandFailure(viewCustomerCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, ViewCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        ViewCustomerCommand viewFirstCommand = new ViewCustomerCommand(INDEX_FIRST);
        ViewCustomerCommand viewSecondCommand = new ViewCustomerCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCustomerCommand viewFirstCommandCopy = new ViewCustomerCommand(INDEX_FIRST);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
