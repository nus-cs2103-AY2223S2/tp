package trackr.model.person;

import trackr.model.ModelEnum;
import trackr.model.item.Item;
import trackr.model.item.UniqueItemList;

/**
 * A list of suppliers that enforces uniqueness between its elements and does not allow nulls.
 * A supplier is considered unique by comparing using {@code Supplier#isSameItem(Item)}. As such, adding and updating of
 * suppliers uses Supplier#isSameItem(Item) for equality to ensure that the supplier being added or updated is unique
 * in terms of identity in the UniqueSupplierList. However, the removal of a supplier uses Supplier#equals(Object) to
 * ensure that the supplier with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Supplier#isSameItem(Item)
 */
public class UniqueSupplierList extends UniqueItemList<Supplier> {

    public UniqueSupplierList() {
        super(ModelEnum.SUPPLIER);
    }
}
