package trackr.logic.commands.menu;

import static trackr.logic.parser.CliSyntax.PREFIX_COST;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PRICE;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditItemCommand;
import trackr.model.ModelEnum;
import trackr.model.item.ItemDescriptor;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.menu.MenuItemDescriptor;

/**
 * Edits the details of an existing item in the menu.
 */
public class EditMenuItemCommand extends EditItemCommand<MenuItem> {

    public static final String COMMAND_WORD = "edit_item";
    public static final String COMMAND_WORD_SHORTCUT = "edit_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the item identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "ITEM NAME] "
            + "[" + PREFIX_PRICE + "ITEM PRICE] "
            + "[" + PREFIX_COST + "ITEM COST]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Cupcake "
            + PREFIX_PRICE + "2"
            + PREFIX_COST + "1";

    /**
     * Creates an EditMenuItemCommand to edit the menu item at the given index.
     *
     * @param index The index of the menu item to be edited.
     * @param editMenuItemDescriptor The details to edit the menu item with.
     */
    public EditMenuItemCommand(Index index, MenuItemDescriptor editMenuItemDescriptor) {
        super(index, new MenuItemDescriptor(editMenuItemDescriptor), ModelEnum.MENUITEM);
    }

    @Override
    protected MenuItem createEditedItem(MenuItem itemToEdit, ItemDescriptor<? super MenuItem> itemDescriptor) {
        assert itemToEdit != null;

        MenuItemDescriptor menuItemDescriptor = (MenuItemDescriptor) itemDescriptor;

        ItemName updatedItemName =
                menuItemDescriptor.getItemName().orElse(itemToEdit.getItemName());
        ItemSellingPrice updatedItemPrice =
                menuItemDescriptor.getItemPrice().orElse(itemToEdit.getItemPrice());
        ItemCost updatedItemCost =
                menuItemDescriptor.getItemCost().orElse(itemToEdit.getItemCost());

        return new MenuItem(updatedItemName, updatedItemPrice, updatedItemCost);
    }
}
