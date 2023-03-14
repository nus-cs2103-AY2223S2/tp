package trackr.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import trackr.model.supplier.Supplier;
import trackr.model.supplier.UniqueSupplierList;

/**
 * Wraps all data at the supplier-list level
 * Duplicates are not allowed (by .isSameSupplier comparison)
 */
public class SupplierList implements ReadOnlySupplierList {

    private final UniqueSupplierList suppliers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        suppliers = new UniqueSupplierList();
    }

    public SupplierList() {}

    /**
     * Creates an supplier list using the Persons in the {@code toBeCopied}
     */
    public SupplierList(ReadOnlySupplierList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the supplier list with {@code supplier}.
     * {@code supplier} must not contain duplicate suppliers.
     */
    public void setSuppliers(List<Supplier> persons) {
        this.suppliers.setSuppliers(persons);
    }

    /**
     * Resets the existing data of this {@code SupplierList} with {@code newData}.
     */
    public void resetData(ReadOnlySupplierList newData) {
        requireNonNull(newData);

        setSuppliers(newData.getSupplierList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code supplier} exists in the supplier list.
     */
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return suppliers.contains(supplier);
    }

    /**
     * Adds a supplier to the supplier list.
     * The supplier must not already exist in the supplier list.
     */
    public void addSupplier(Supplier p) {
        suppliers.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedSupplier}.
     * {@code target} must exist in the supplier list.
     * The supplier identity of {@code editedSupplier} must not be the same as another existing supplier
     * in the address book.
     */
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireNonNull(editedSupplier);

        suppliers.setSupplier(target, editedSupplier);
    }

    /**
     * Removes {@code key} from this {@code SupplierList}.
     * {@code key} must exist in the supplier list.
     */
    public void removeSupplier(Supplier key) {
        suppliers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return suppliers.asUnmodifiableObservableList().size() + " suppliers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Supplier> getSupplierList() {
        return suppliers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SupplierList // instanceof handles nulls
                && suppliers.equals(((SupplierList) other).suppliers));
    }

    @Override
    public int hashCode() {
        return suppliers.hashCode();
    }
}
