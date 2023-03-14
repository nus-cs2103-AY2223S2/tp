package trackr.model.supplier;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.supplier.exceptions.DuplicateSupplierException;
import trackr.model.supplier.exceptions.SupplierNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality to ensure that the person being added or updated is unique
 * in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) to
 * ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Supplier#isSameSupplier(Supplier)
 */
public class UniqueSupplierList implements Iterable<Supplier> {

    private final ObservableList<Supplier> internalList = FXCollections.observableArrayList();
    private final ObservableList<Supplier> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Supplier as the given argument.
     */
    public boolean contains(Supplier toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSupplier);
    }

    /**
     * Adds a Supplier to the list.
     * The supplier must not already exist in the list.
     */
    public void add(Supplier toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSupplierException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the supplier {@code target} in the list with {@code editedSupplier}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedSupplier} must not be the same as another existing supplier in the list.
     */
    public void setSupplier(Supplier target, Supplier editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SupplierNotFoundException();
        }

        if (!target.isSameSupplier(editedPerson) && contains(editedPerson)) {
            throw new DuplicateSupplierException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Supplier toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SupplierNotFoundException();
        }
    }

    public void setSuppliers(UniqueSupplierList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code suppliers}.
     * {@code suppliers} must not contain duplicate suppliers.
     */
    public void setSuppliers(List<Supplier> suppliers) {
        requireAllNonNull(suppliers);
        if (!suppliersAreUnique(suppliers)) {
            throw new DuplicateSupplierException();
        }

        internalList.setAll(suppliers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Supplier> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Supplier> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSupplierList // instanceof handles nulls
                        && internalList.equals(((UniqueSupplierList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean suppliersAreUnique(List<Supplier> suppliers) {
        for (int i = 0; i < suppliers.size() - 1; i++) {
            for (int j = i + 1; j < suppliers.size(); j++) {
                if (suppliers.get(i).isSameSupplier(suppliers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
