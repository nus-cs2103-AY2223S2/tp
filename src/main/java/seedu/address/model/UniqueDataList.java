package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of data that enforces uniqueness between its elements and does not allow nulls.
 * A data is considered unique by comparing using {@code isSameChecker.test(T, T)}. As such, adding and updating of
 * data uses {@code isSameChecker.test(T, T)} for equality so as to ensure that the data being added or updated is
 * unique in the UniqueDataList. However, the removal of a data uses {@code T#equals(Object)} so as to ensure that
 * the data with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueDataList<T> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private final BiPredicate<T, T> isSameChecker;
    private final Supplier<RuntimeException> duplicateExceptionCreator;
    private final Supplier<RuntimeException> notFoundExceptionCreator;

    /**
     * Constructs a {@code UniqueDataList<T>}.
     *
     * @param isSameChecker Use to check if 2 data are the same.
     * @param duplicateExceptionCreator Creates any exception thrown due to duplicate data.
     * @param notFoundExceptionCreator Creates any exception thrown due to data not found.
     */
    public UniqueDataList(BiPredicate<T, T> isSameChecker,
            Supplier<RuntimeException> duplicateExceptionCreator,
            Supplier<RuntimeException> notFoundExceptionCreator) {

        this.isSameChecker = isSameChecker;
        this.duplicateExceptionCreator = duplicateExceptionCreator;
        this.notFoundExceptionCreator = notFoundExceptionCreator;
    }

    /**
     * Returns true if the list contains an equivalent data as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch((data) -> isSameChecker.test(toCheck, data));
    }

    /**
     * Adds a data to the list.
     * The data must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw duplicateExceptionCreator.get();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the data {@code target} in the list with {@code editedData}.
     * {@code target} must exist in the list.
     * {@code editedData} must not be the same as another existing data in the list.
     */
    public void setData(T target, T editedData) {
        requireAllNonNull(target, editedData);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw notFoundExceptionCreator.get();
        }

        if (!isSameChecker.test(target, editedData) && contains(editedData)) {
            throw duplicateExceptionCreator.get();
        }

        internalList.set(index, editedData);
    }

    /**
     * Removes the equivalent data from the list.
     * The data must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw notFoundExceptionCreator.get();
        }
    }

    public void setAllData(UniqueDataList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code data}.
     * {@code data} must not contain duplicate data.
     */
    public void setAllData(List<T> data) {
        requireAllNonNull(data);
        if (!dataAreUnique(data)) {
            throw duplicateExceptionCreator.get();
        }

        internalList.setAll(data);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDataList<?> // instanceof handles nulls
                        && internalList.equals(((UniqueDataList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code data} contains only unique data.
     */
    private boolean dataAreUnique(List<T> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (isSameChecker.test(data.get(i), data.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
