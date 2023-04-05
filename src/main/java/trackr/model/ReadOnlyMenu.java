package trackr.model;

import trackr.model.item.ReadOnlyItemList;
import trackr.model.menu.MenuItem;

/**
 * Unmodifiable view of a menu item list.
 */
public interface ReadOnlyMenu extends ReadOnlyItemList<MenuItem> {
}
