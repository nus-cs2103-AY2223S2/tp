package trackr.model;

import trackr.model.item.ReadOnlyItemList;
import trackr.model.person.Supplier;

/**
 * Unmodifiable view of a supplier list.
 */
//@@author liumc-sg-reused
public interface ReadOnlySupplierList extends ReadOnlyItemList<Supplier> {

}
