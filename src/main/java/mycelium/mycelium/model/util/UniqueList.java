package mycelium.mycelium.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    // TODO a "set" method

    /**
     * Removes an item from the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException(toRemove);
        }
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
