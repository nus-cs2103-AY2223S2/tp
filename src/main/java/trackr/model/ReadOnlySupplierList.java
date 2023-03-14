package trackr.model;

import javafx.collections.ObservableList;
import trackr.model.supplier.Supplier;

/**
 * Unmodifiable view of an supplier list.
 */
public interface ReadOnlySupplierList {

    /**
     * Returns an unmodifiable view of the Supplier list.
     * This list will not contain any duplicate suppliers.
     */
    ObservableList<Supplier> getSupplierList();

}
