package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
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
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.testutil.CustomerBuilder;

public class AppendCustomerNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String nonEmptyString = "Test Note";

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppendCustomerNoteCommand(null, ""));
    }

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppendCustomerNoteCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_unfilteredList_success() {
        Index indexLastCustomer = Index.fromOneBased(model.getFilteredCustomerList().size());
        Customer lastCustomer = model.getFilteredCustomerList().get(indexLastCustomer.getZeroBased());

        String newNote = lastCustomer.getNote().value + nonEmptyString;
        Customer editedCustomer = new CustomerBuilder(lastCustomer).withNote(newNote).build();
        AppendCustomerNoteCommand appendCustomerNoteCommand = new AppendCustomerNoteCommand(
                indexLastCustomer, nonEmptyString);

        String expectedMessage = String.format(
                AppendCustomerNoteCommand.MESSAGE_APPEND_NOTE_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCustomer(lastCustomer, editedCustomer);

        assertCommandSuccess(appendCustomerNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Customer customerInFilteredList = model.getFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        String newNote = customerInFilteredList.getNote().value + nonEmptyString;
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withNote(newNote).build();
        AppendCustomerNoteCommand appendCustomerNoteCommand = new AppendCustomerNoteCommand(
                INDEX_FIRST, nonEmptyString);

        String expectedMessage = String.format(AppendCustomerNoteCommand.MESSAGE_APPEND_NOTE_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(appendCustomerNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCustomerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        AppendCustomerNoteCommand appendCustomerNoteCommand = new AppendCustomerNoteCommand(
                outOfBoundIndex, nonEmptyString);

        assertCommandFailure(appendCustomerNoteCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, AppendCustomerNoteCommand.MESSAGE_USAGE));
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

        AppendCustomerNoteCommand appendCustomerNoteCommand = new AppendCustomerNoteCommand(
                outOfBoundIndex, nonEmptyString);

        assertCommandFailure(appendCustomerNoteCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, AppendCustomerNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final AppendCustomerNoteCommand standardCommand = new AppendCustomerNoteCommand(
                INDEX_FIRST, nonEmptyString);

        // same values -> returns true
        AppendCustomerNoteCommand commandWithSameValues = new AppendCustomerNoteCommand(
                INDEX_FIRST, nonEmptyString);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AppendCustomerNoteCommand(
                INDEX_SECOND, nonEmptyString)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AppendCustomerNoteCommand(
                INDEX_FIRST, nonEmptyString + " EXTRA")));
    }
}
