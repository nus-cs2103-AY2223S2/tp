package tfifteenfour.clipboard.model;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateGroupException;


/**
 * A list that enforces its items to be uniques
 */
public abstract class UniqueList<T> implements Iterable<T> {

    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    protected final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    protected final FilteredList<T> filteredList = new FilteredList<>(internalList);

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public abstract boolean contains(T toCheck);

    public abstract UniqueList<T> copy();

    /**
     * Adds a item to the list.
     * The item must not already exist in the list.
     */
    public abstract void add(T toAdd);

    /**
     * Replaces an item in the list with a new item
     * @param target item to replace
     * @param newItem item that is replacing
     */
    public abstract void set(T target, T newItem);

    protected abstract boolean elementsAreUnique(List<T> items);

    /**
     * Removes the specified item from the list
     * @param toRemove item to remove
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ElementNotFoundException();
        }
    }

    public void setInternalList(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setInternalList(List<T> items) {
        requireAllNonNull(items);
        if (!elementsAreUnique(items)) {
            throw new DuplicateGroupException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as a modifiable {@code ObservableList}.
     */
    public ObservableList<T> asModifiableObservableList() {
        return internalList;
    }

    public ObservableList<T> asUnmodifiableFilteredList() {
        return FXCollections.unmodifiableObservableList(filteredList);
    }

    public ObservableList<T> asModifiableFilteredList() {
        return filteredList;
    }

    public void updateFilterPredicate(Predicate<T> predicate) {
        this.filteredList.setPredicate(predicate);
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
