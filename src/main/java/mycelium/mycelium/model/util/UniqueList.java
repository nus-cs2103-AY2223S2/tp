package mycelium.mycelium.model.util;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.model.util.exceptions.ItemNotFoundException;

/**
 * A list with invariant that every item is unique. The items must implement {@link IsSame}, which is used to check
 * if uniqueness is violated, rather than {@code equals}.
 */
public class UniqueList<T extends IsSame<T>> implements Iterable<T> {
    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the current list contains some object.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds an item to the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException(toAdd);
        }
        internalList.add(toAdd);
    }

    /**
     * Removes an item from the list. Note that the item to remove to checked via {@link IsSame}, not {@code equals}.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        FilteredList<T> filtered = internalList.filtered(toRemove::isSame);
        if (filtered.size() == 0) {
            throw new ItemNotFoundException(toRemove);
        }
        assert filtered.size() == 1 : "Invariant violated: list contains duplicate items";
        boolean isRemoved = internalList.remove(filtered.get(0));
        assert isRemoved : "Invariant violated: list does not contain item to remove";
    }

    /**
     * Replaces an item with another at the same index. The two items do not have to be the same.
     */
    public void setItem(T target, T newItem) {
        requireAllNonNull(target, newItem);
        int[] idxs = IntStream.range(0, internalList.size()).filter(i -> internalList.get(i).isSame(target)).toArray();
        if (idxs.length == 0) {
            throw new ItemNotFoundException(target);
        }
        assert idxs.length == 1 : "Invariant violated: list contains duplicate items";
        if (!target.isSame(newItem) && contains(newItem)) {
            throw new DuplicateItemException(newItem);
        }
        internalList.set(idxs[0], newItem);
    }

    /**
     * Replaces the current list with another.
     */
    public void setItems(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the current list with another. Items in the list must be unique.
     */
    public void setItems(List<T> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }
        internalList.setAll(items);
    }

    private boolean itemsAreUnique(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSame(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Returns an immutable reference to the internal list.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UniqueList<?> that = (UniqueList<?>) o;
        return Objects.equals(internalList, that.internalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList);
    }
}
