package seedu.address.model;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.exceptions.DataNotFoundException;
import seedu.address.model.exceptions.DuplicateDataException;

/**
 * A {@code UniqueDataList} that is sorted using {@code T#compareTo(T)}.
 *
 * @see UniqueDataList
 */
public class SortedUniqueDataList<T extends Comparable<T>> extends UniqueDataList<T> {
    /**
     * Constructs a {@code SortedUniqueDataList<T>}.
     *
     * @param isSameChecker Checks if 2 data are the same.
     * @param duplicateExceptionCreator Creates an exception to throw in the event that duplicate data is detected.
     * @param notFoundExceptionCreator Creates an exception to throw in the event that a data is not found.
     */
    public SortedUniqueDataList(BiPredicate<T, T> isSameChecker,
            Supplier<DuplicateDataException> duplicateExceptionCreator,
            Supplier<DataNotFoundException> notFoundExceptionCreator) {

        super(isSameChecker, duplicateExceptionCreator, notFoundExceptionCreator);
    }

    @Override
    public ObservableList<T> asUnmodifiableObservableList() {
        return new SortedList<T>(super.asUnmodifiableObservableList(), T::compareTo);
    }
}
