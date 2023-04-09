package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_B;
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
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.OrderBuilder;

public class SetOrderNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetOrderNoteCommand(null, new Note("")));
    }

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetOrderNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_unfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        Order editedOrder = new OrderBuilder(lastOrder).withNote(VALID_NOTE_B).build();
        SetOrderNoteCommand setOrderNoteCommand = new SetOrderNoteCommand(indexLastOrder,
                new Note(VALID_NOTE_B));

        String expectedMessage = String.format(SetOrderNoteCommand.MESSAGE_SET_NOTE_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccess(setOrderNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        SetOrderNoteCommand setOrderNoteCommand = new SetOrderNoteCommand(
                outOfBoundIndex, new Note(VALID_NOTE_B));

        assertCommandFailure(setOrderNoteCommand, model,
                String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, SetOrderNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final SetOrderNoteCommand standardCommand = new SetOrderNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_A));

        // same values -> returns true
        SetOrderNoteCommand commandWithSameValues = new SetOrderNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_A));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SetOrderNoteCommand(INDEX_SECOND,
                new Note(VALID_NOTE_A))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new SetOrderNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_B))));
    }
}
