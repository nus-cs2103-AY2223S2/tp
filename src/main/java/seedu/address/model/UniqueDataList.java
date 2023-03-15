package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.DataNotFoundException;
import seedu.address.model.exceptions.DuplicateDataException;

/**
 * A list of data that enforces uniqueness between its elements and does not allow nulls.<p>
 *
 * A data is considered unique by comparing using {@code isSameChecker.test(T, T)}. As such, adding and updating of
 * data uses {@code isSameChecker.test(T, T)} for equality so as to ensure that the data being added or updated is
 * unique in the {@code UniqueDataList}.<p>
 *
 * However, the removal of a data uses {@code T#equals(Object)} so as to ensure that the data with exactly the same
 * fields will be removed.<p>
 *
 * Supports a minimal set of list operations.
 */
public class UniqueDataList<T> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    private final BiPredicate<T, T> isSameChecker;
    private final Supplier<DuplicateDataException> duplicateExceptionCreator;
    private final Supplier<DataNotFoundException> notFoundExceptionCreator;

    /**
     * Constructs a {@code UniqueDataList<T>}.
     *
     * @param isSameChecker Checks if 2 data are the same.
     * @param duplicateExceptionCreator Creates an exception to throw in the event that duplicate data is detected.
     * @param notFoundExceptionCreator Creates an exception to throw in the event that a data is not found.
     */
    public UniqueDataList(BiPredicate<T, T> isSameChecker,
            Supplier<DuplicateDataException> duplicateExceptionCreator,
            Supplier<DataNotFoundException> notFoundExceptionCreator) {

        this.isSameChecker = isSameChecker;
        this.duplicateExceptionCreator = duplicateExceptionCreator;
        this.notFoundExceptionCreator = notFoundExceptionCreator;
    }

    /**
     * Returns true if the list contains a data equivalent to {@code toCheck}.
     *
     * @param toCheck The data to check if it is in the list.
     * @return True if the list contains a data equivalent to {@code toCheck}. Otheriwse, false.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch((data) -> isSameChecker.test(toCheck, data));
    }

    /**
     * Adds the specified data {@code toAdd} to the list.<p>
     * {@code toAdd} must not already exist in the list.
     *
     * @param toAdd The data to add.
     * @throws DuplicateDataException Indicates that {@code toAdd} already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw duplicateExceptionCreator.get();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the data {@code target} in the list with {@code editedData}.<p>
     * {@code target} must exist in the list.<p>
     * {@code editedData} must not be the same as another existing data in the list.
     *
     * @param target The data to be replaced.
     * @param editedData The data that will replace.
     * @throws DataNotFoundException Indicates that {@code target} does not exist in the list.
     * @throws DuplicateDataException Indicates that {@code editedData} is the same as another existing data
     *                                in the list.
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
     * Removes the data equivalent to {@code toRemove} from the list.<p>
     * The data must exist in the list.
     *
     * @param toRemove The data whose equivalent is to be removed from the list.
     * @throws DataNotFoundException Indicates that the data does not exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw notFoundExceptionCreator.get();
        }
    }

    /**
     * Replaces the content of this list with {@code replacement}.
     *
     * @param replacement The list containing the data that will replace.
     */
    public void setAllData(UniqueDataList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code data}.<p>
     * {@code data} must not contain duplicate data.
     *
     * @param data The list containing the data that will replace.
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
     *
     * @return The backing list as an unmodifiable {@code ObservableList}.
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
     *
     * @param data The list to be check that it's elements are unique.
     * @return True if {@code data} contains only unique data. Otherwise, false.
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
