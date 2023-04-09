package trackr.model;

import trackr.model.item.ItemList;
import trackr.model.person.Supplier;

/**
 * Wraps all data at the supplier-list level.
 * Duplicates are not allowed (by .isSameItem comparison).
 */
public class SupplierList extends ItemList<Supplier> implements ReadOnlySupplierList {

    public SupplierList() {
        super();
    }

    /**
     * Creates a SupplierList using the Suppliers in the {@code toBeCopied}.
     */
    public SupplierList(ReadOnlySupplierList toBeCopied) {
        super(toBeCopied);
    }
}
