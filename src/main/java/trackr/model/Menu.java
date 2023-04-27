package trackr.model;

import trackr.model.item.ItemList;
import trackr.model.menu.MenuItem;

/**
 * Wraps all data at the menu item-list level.
 * Duplicates are not allowed (by .isSameItem comparison).
 */
public class Menu extends ItemList<MenuItem> implements ReadOnlyMenu {

    public Menu() {
        super();
    }

    /**
     * Creates a Menu using the Menu Items in the {@code toBeCopied}.
     */
    public Menu(ReadOnlyMenu toBeCopied) {
        super(toBeCopied);
    }
}
