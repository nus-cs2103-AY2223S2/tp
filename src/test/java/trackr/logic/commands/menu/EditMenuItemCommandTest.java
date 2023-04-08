package trackr.logic.commands.menu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_CHOCOLATE_COOKIE_M;
import static trackr.logic.commands.CommandTestUtil.DESC_CUPCAKES_M;
import static trackr.logic.commands.CommandTestUtil.VALID_MENU_ITEM_NAME_CHOCOLATE_COOKIES;
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
import trackr.model.Menu;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.OrderList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.menu.MenuItem;
import trackr.model.menu.MenuItemDescriptor;
import trackr.testutil.MenuItemDescriptorBuilder;
import trackr.testutil.MenuItemBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMenuItemCommand.
 */
public class EditMenuItemCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {
        MenuItem editedMenuItem = new MenuItemBuilder().build();
        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder(editedMenuItem).build();
        EditMenuItemCommand editCommand = new EditMenuItemCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage = String.format(EditMenuItemCommand.MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.MENUITEM.toString().toLowerCase(),
                editedMenuItem);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setItem(model.getFilteredMenu().get(0), editedMenuItem, ModelEnum.MENUITEM);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Index indexLastMenuItem = Index.fromOneBased(model.getFilteredMenu().size());
        MenuItem lastMenuItem = model.getFilteredMenu().get(indexLastMenuItem.getZeroBased());

        MenuItemBuilder menuItemInList = new MenuItemBuilder(lastMenuItem);
        MenuItem editedMenuItem = menuItemInList.withItemName("New Item").withItemPrice("100.00")
                .withItemCost("1.00").build();

        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder().withName("New Item")
                .withPrice("100.00").withCost("1.00").build();
        EditMenuItemCommand editCommand = new EditMenuItemCommand(indexLastMenuItem, descriptor);

        String expectedMessage = String.format(EditMenuItemCommand.MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.MENUITEM.toString().toLowerCase(),
                editedMenuItem);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setItem(lastMenuItem, editedMenuItem, ModelEnum.MENUITEM);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() throws ParseException {
        EditMenuItemCommand editCommand = new EditMenuItemCommand(INDEX_FIRST_OBJECT, new MenuItemDescriptor());
        MenuItem editedMenuItem = model.getFilteredMenu().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage = String.format(EditMenuItemCommand.MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.MENUITEM.toString().toLowerCase(),
                editedMenuItem);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws ParseException {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);

        MenuItem MenuItemInFilteredList = model.getFilteredMenu().get(INDEX_FIRST_OBJECT.getZeroBased());
        MenuItem editedMenuItem = new MenuItemBuilder(MenuItemInFilteredList).withItemName(VALID_MENU_ITEM_NAME_CHOCOLATE_COOKIES).build();
        EditMenuItemCommand editCommand = new EditMenuItemCommand(INDEX_FIRST_OBJECT,
                new MenuItemDescriptorBuilder().withName(VALID_MENU_ITEM_NAME_CHOCOLATE_COOKIES).build());

        String expectedMessage = String.format(EditMenuItemCommand.MESSAGE_EDIT_ITEM_SUCCESS,
                ModelEnum.MENUITEM.toString().toLowerCase(),
                editedMenuItem);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new Menu(model.getMenu()),
                new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setItem(model.getFilteredMenu().get(0), editedMenuItem, ModelEnum.MENUITEM);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMenuItemUnfilteredList_failure() {
        MenuItem firstMenuItem = model.getFilteredMenu().get(INDEX_FIRST_OBJECT.getZeroBased());
        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder(firstMenuItem).build();
        EditMenuItemCommand editCommand = new EditMenuItemCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editCommand,
                model,
                String.format(EditMenuItemCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    @Test
    public void execute_duplicateMenuItemFilteredList_failure() {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);

        // edit MenuItem in filtered list into a duplicate in address book
        MenuItem MenuItemInList = model.getMenu().getItemList().get(INDEX_SECOND_OBJECT.getZeroBased());
        EditMenuItemCommand editCommand = new EditMenuItemCommand(INDEX_FIRST_OBJECT,
                new MenuItemDescriptorBuilder(MenuItemInList).build());

        assertCommandFailure(editCommand,
                model,
                String.format(EditMenuItemCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    @Test
    public void execute_invalidMenuItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMenu().size() + 1);
        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder().withName(VALID_MENU_ITEM_NAME_CHOCOLATE_COOKIES).build();
        EditMenuItemCommand editCommand = new EditMenuItemCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand,
                        model,
                        String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                                ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidMenuItemIndexFilteredList_failure() {
        showMenuItemAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMenu().getItemList().size());

        EditMenuItemCommand editCommand = new EditMenuItemCommand(outOfBoundIndex,
                new MenuItemDescriptorBuilder().withName(VALID_MENU_ITEM_NAME_CHOCOLATE_COOKIES).build());

        assertCommandFailure(editCommand,
                        model,
                        String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                                ModelEnum.MENUITEM.toString().toLowerCase()));
    }

    @Test
    public void equals() {
        final EditMenuItemCommand standardCommand = new EditMenuItemCommand(INDEX_FIRST_OBJECT, DESC_CHOCOLATE_COOKIE_M);

        // same values -> returns true
        MenuItemDescriptor copyDescriptor = new MenuItemDescriptor(DESC_CHOCOLATE_COOKIE_M);
        EditMenuItemCommand commandWithSameValues = new EditMenuItemCommand(INDEX_FIRST_OBJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearMenuItemCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMenuItemCommand(INDEX_SECOND_OBJECT, DESC_CHOCOLATE_COOKIE_M)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMenuItemCommand(INDEX_FIRST_OBJECT, DESC_CUPCAKES_M)));
    }

}
