package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.OrderBuilder;

public class AppendOrderNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String nonEmptyString = "Test Note";

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppendOrderNoteCommand(null, ""));
    }

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppendOrderNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_unfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        String newNote = lastOrder.getNote().value + nonEmptyString;
        Order editedOrder = new OrderBuilder(lastOrder).withNote(newNote).build();
        AppendOrderNoteCommand appendOrderNoteCommand = new AppendOrderNoteCommand(
                indexLastOrder, nonEmptyString);

        String expectedMessage = String.format(
                AppendOrderNoteCommand.MESSAGE_APPEND_NOTE_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccess(appendOrderNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        AppendOrderNoteCommand appendOrderNoteCommand = new AppendOrderNoteCommand(
                outOfBoundIndex, nonEmptyString);

        assertCommandFailure(appendOrderNoteCommand, model,
                String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, AppendOrderNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final AppendOrderNoteCommand standardCommand = new AppendOrderNoteCommand(
                INDEX_FIRST, nonEmptyString);

        // same values -> returns true
        AppendOrderNoteCommand commandWithSameValues = new AppendOrderNoteCommand(
                INDEX_FIRST, nonEmptyString);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AppendOrderNoteCommand(
                INDEX_SECOND, nonEmptyString)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AppendOrderNoteCommand(
                INDEX_FIRST, nonEmptyString + " EXTRA")));
    }
}
