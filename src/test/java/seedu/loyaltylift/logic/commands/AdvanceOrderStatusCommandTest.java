package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;

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
        Order orderToAdvance = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        AdvanceOrderStatusCommand advanceOrderStatusCommand = new AdvanceOrderStatusCommand(INDEX_FIRST);

        String expectedMessage = String.format(advanceOrderStatusCommand.MESSAGE_ADVANCE_STATUS_SUCCESS,
                orderToAdvance, orderToAdvance.getStatus().getLatestStatus());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, null, null);
        System.out.println(expectedCommandResult.getFeedbackToUser());
        System.out.println(expectedMessage);
        assertCommandSuccess(advanceOrderStatusCommand, model, expectedCommandResult, model);
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
