package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_ORDER;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewOrderCommand}.
 */
public class ViewOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Order orderToView = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        ViewOrderCommand viewOrderCommand = new ViewOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(viewOrderCommand.MESSAGE_VIEW_ORDER_SUCCESS, orderToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, LIST_AND_SHOW_ORDER);

        assertCommandSuccess(viewOrderCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        ViewOrderCommand viewOrderCommand = new ViewOrderCommand(outOfBoundIndex);

        assertCommandFailure(viewOrderCommand, model,
                String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, ViewOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        ViewOrderCommand viewOrderCommand = new ViewOrderCommand(outOfBoundIndex);

        assertCommandFailure(viewOrderCommand, model,
                String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, ViewOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        ViewOrderCommand viewFirstCommand = new ViewOrderCommand(INDEX_FIRST);
        ViewOrderCommand viewSecondCommand = new ViewOrderCommand(INDEX_SECOND);

        // same object
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values
        ViewOrderCommand viewFirstCommandCopy = new ViewOrderCommand(INDEX_FIRST);
        assertEquals(viewFirstCommand, viewFirstCommandCopy);

        // different types -> not equals
        assertNotEquals(1, viewFirstCommand);

        // null -> not equals
        assertNotEquals(null, viewFirstCommand);

        // different customer -> not equals
        assertNotEquals(viewFirstCommand, viewSecondCommand);
    }
}
