package mycelium.mycelium.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.model.util.exceptions.ItemNotFoundException;

public class UniqueList<T extends IsSame<T>> implements Iterable<T>{
    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException(toAdd);
        }
        internalList.add(toAdd);
    }

    // TODO a "set" method

    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException(toRemove);
        }
    }

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
