package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.model.order.StatusUpdate.DATE_FORMATTER;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.OrderBuilder;

public class AdvanceOrderStatusCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        AdvanceOrderStatusCommand advanceOrderStatusCommand = new AdvanceOrderStatusCommand(outOfBoundIndex);

        assertCommandFailure(advanceOrderStatusCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        LocalDate dateToday = LocalDate.now();
        String formattedDate = dateToday.format(DATE_FORMATTER);
        Order orderToAdvance = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        AdvanceOrderStatusCommand advanceOrderStatusCommand = new AdvanceOrderStatusCommand(INDEX_FIRST);

        Order advancedOrder = new OrderBuilder(orderToAdvance).withNextStatus(formattedDate).build();
        String expectedMessage = String.format(advanceOrderStatusCommand.MESSAGE_ADVANCE_STATUS_SUCCESS,
                advancedOrder, advancedOrder.getStatus().getLatestStatus());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setOrder(orderToAdvance, advancedOrder);
        assertCommandSuccess(advanceOrderStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final AdvanceOrderStatusCommand standardCommand = new AdvanceOrderStatusCommand(INDEX_FIRST);

        // same values -> returns true
        AdvanceOrderStatusCommand commandWithSameValues = new AdvanceOrderStatusCommand(INDEX_FIRST);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AdvanceOrderStatusCommand(INDEX_SECOND)));
    }
}
