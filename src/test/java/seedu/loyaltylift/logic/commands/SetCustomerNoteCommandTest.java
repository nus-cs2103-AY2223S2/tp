package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
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
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.testutil.CustomerBuilder;

public class SetCustomerNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetCustomerNoteCommand(null, new Note("")));
    }

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetCustomerNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_unfilteredList_success() {
        Index indexLastCustomer = Index.fromOneBased(model.getFilteredCustomerList().size());
        Customer lastCustomer = model.getFilteredCustomerList().get(indexLastCustomer.getZeroBased());

        Customer editedCustomer = new CustomerBuilder(lastCustomer).withNote(VALID_NOTE_BOB).build();
        SetCustomerNoteCommand setCustomerNoteCommand = new SetCustomerNoteCommand(indexLastCustomer,
                new Note(VALID_NOTE_BOB));

        String expectedMessage = String.format(SetCustomerNoteCommand.MESSAGE_SET_NOTE_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCustomer(lastCustomer, editedCustomer);

        assertCommandSuccess(setCustomerNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Customer customerInFilteredList = model.getFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withNote(VALID_NOTE_BOB).build();
        SetCustomerNoteCommand setCustomerNoteCommand = new SetCustomerNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_BOB));

        String expectedMessage = String.format(SetCustomerNoteCommand.MESSAGE_SET_NOTE_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(setCustomerNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCustomerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        SetCustomerNoteCommand setCustomerNoteCommand = new SetCustomerNoteCommand(
                outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(setCustomerNoteCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, SetCustomerNoteCommand.MESSAGE_USAGE));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidCustomerIndexFilteredList_failure() {
        showCustomerAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        SetCustomerNoteCommand setCustomerNoteCommand = new SetCustomerNoteCommand(
                outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(setCustomerNoteCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, SetCustomerNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final SetCustomerNoteCommand standardCommand = new SetCustomerNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_AMY));

        // same values -> returns true
        SetCustomerNoteCommand commandWithSameValues = new SetCustomerNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SetCustomerNoteCommand(INDEX_SECOND,
                new Note(VALID_NOTE_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new SetCustomerNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_BOB))));
    }
}
