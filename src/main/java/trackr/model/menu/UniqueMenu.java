package trackr.model.menu;

import trackr.model.ModelEnum;
import trackr.model.item.Item;
import trackr.model.item.UniqueItemList;

/**
 * A list of MenuItems that enforces uniqueness between its elements and does not allow nulls.
 * A MenuItem is considered unique by comparing using {@code MenuItem#isSameItem(Item)}.
 * As such, adding and updating of MenuItems uses MenuItem#isSameItem(Item) for equality
 * to ensure that the MenuItem being added or updated is unique
 * in terms of identity in the UniqueMenuItemList.
 * However, the removal of a MenuItem uses MenuItem#equals(Object) to
 * ensure that the MenuItem with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see MenuItem#isSameItem(Item)
 */
public class UniqueMenu extends UniqueItemList<MenuItem> {

    public UniqueMenu() {
        super(ModelEnum.MENUITEM);
    }
}
