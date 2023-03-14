package trackr.model;

import javafx.collections.ObservableList;
import trackr.model.supplier.Supplier;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the Supplier list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Supplier> getSupplierList();

}
