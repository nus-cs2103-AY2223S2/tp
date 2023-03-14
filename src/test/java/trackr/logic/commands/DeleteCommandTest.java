package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showPersonAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static trackr.testutil.TypicalSuppliers.getTypicalAddressBook;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.supplier.Supplier;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Supplier personToDelete = model.getFilteredSupplierList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteSupplierCommand deleteCommand = new DeleteSupplierCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSupplierList().size() + 1);
        DeleteSupplierCommand deleteCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Supplier personToDelete = model.getFilteredSupplierList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteSupplierCommand deleteCommand = new DeleteSupplierCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSupplierList().size());

        DeleteSupplierCommand deleteCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSupplierCommand deleteFirstCommand = new DeleteSupplierCommand(INDEX_FIRST_PERSON);
        DeleteSupplierCommand deleteSecondCommand = new DeleteSupplierCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteSupplierCommand deleteFirstCommandCopy = new DeleteSupplierCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredSupplierList(p -> false);

        assertTrue(model.getFilteredSupplierList().isEmpty());
    }
}
