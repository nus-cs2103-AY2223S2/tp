package trackr.logic.commands.menu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showMenuItemAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.menu.MenuItem;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMenuItemCommand}.
 */
public class DeleteMenuItemCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws ParseException {
        MenuItem menuItemToDelete = model.getFilteredMenu().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteMenuItemCommand deleteMenuItemCommand = new DeleteMenuItemCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteMenuItemCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.MENUITEM,
                menuItemToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        expectedModel.deleteItem(menuItemToDelete, ModelEnum.MENUITEM);

        assertCommandSuccess(deleteMenuItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMenu().size() + 1);
        DeleteMenuItemCommand deleteMenuItemCommand = new DeleteMenuItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteMenuItemCommand, model,
                            String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                                        ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    @Test
    public void execute_validIndexFilteredList_success() throws ParseException {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);

        MenuItem menuItemToDelete = model.getFilteredMenu().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteMenuItemCommand deleteMenuItemCommand = new DeleteMenuItemCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteMenuItemCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.MENUITEM,
                menuItemToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        expectedModel.deleteItem(menuItemToDelete, ModelEnum.MENUITEM);
        showNoSupplier(expectedModel);

        assertCommandSuccess(deleteMenuItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSupplierList().getItemList().size());

        DeleteMenuItemCommand deleteCommand = new DeleteMenuItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model,
                            String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                                        ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    @Test
    public void equals() {
        DeleteMenuItemCommand deleteFirstCommand = new DeleteMenuItemCommand(INDEX_FIRST_OBJECT);
        DeleteMenuItemCommand deleteSecondCommand = new DeleteMenuItemCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMenuItemCommand deleteFirstCommandCopy = new DeleteMenuItemCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different supplier -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSupplier(Model model) {
        model.updateFilteredItemList(p -> false, ModelEnum.MENUITEM);

        assertTrue(model.getFilteredMenu().isEmpty());
    }
}
