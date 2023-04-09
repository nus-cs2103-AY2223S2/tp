package trackr.logic.commands.menu;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Menu;
import trackr.model.ModelEnum;
import trackr.model.ReadOnlyMenu;
import trackr.model.item.Item;
import trackr.model.menu.MenuItem;
import trackr.testutil.MenuItemBuilder;
import trackr.testutil.TestUtil.ModelStub;

public class AddMenuItemCommandTest {

    @Test
    public void constructor_nullMenuItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMenuItemCommand(null));
    }

    @Test
    public void execute_menuItemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMenuItemAdded modelStub = new ModelStubAcceptingMenuItemAdded();
        MenuItem validMenuItem = new MenuItemBuilder().build();

        CommandResult commandResult = new AddMenuItemCommand(validMenuItem).execute(modelStub);

        assertEquals(String.format(AddMenuItemCommand.MESSAGE_SUCCESS, ModelEnum.MENUITEM, validMenuItem),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMenuItem), modelStub.menuItemAdded);
    }

    @Test
    public void execute_duplicateMenuItem_throwsCommandException() {
        MenuItem validMenuItem = new MenuItemBuilder().build();
        AddMenuItemCommand addMenuItemCommand = new AddMenuItemCommand(validMenuItem);
        ModelStub modelStub = new ModelStubWithMenuItem(validMenuItem);

        assertThrows(CommandException.class,
                String.format(AddMenuItemCommand.MESSAGE_DUPLICATE_ITEM,
                        ModelEnum.MENUITEM, ModelEnum.MENUITEM), () -> addMenuItemCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        MenuItem alice = new MenuItemBuilder().withItemName("Apples").build();
        MenuItem bob = new MenuItemBuilder().withItemName("Banana").build();
        AddMenuItemCommand addAliceCommand = new AddMenuItemCommand(alice);
        AddMenuItemCommand addBobCommand = new AddMenuItemCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddMenuItemCommand addAliceCommandCopy = new AddMenuItemCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different MenuItem -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single MenuItem.
     */
    private class ModelStubWithMenuItem extends ModelStub {
        private final MenuItem menuItem;

        ModelStubWithMenuItem(MenuItem menuItem) {
            requireNonNull(menuItem);
            this.menuItem = menuItem;
        }

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return this.menuItem.isSameItem((MenuItem) item);
        }
    }

    /**
     * A Model stub that always accept the MenuItem being added.
     */
    private class ModelStubAcceptingMenuItemAdded extends ModelStub {
        final ArrayList<MenuItem> menuItemAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return menuItemAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            menuItemAdded.add((MenuItem) item);
        }

        @Override
        public ReadOnlyMenu getMenu() {
            return new Menu();
        }
    }

}
