package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ultron.model.opening.exceptions.DuplicateOpeningException;
import seedu.ultron.model.opening.exceptions.OpeningNotFoundException;

/**
 * A list of openings that enforces uniqueness between its elements and does not allow nulls.
 * A opening is considered unique by comparing using {@code Opening#isSameOpening(Opening)}.
 * As such, adding and updating of openings uses Opening#isSameOpening(Opening) for equality
 * so as to ensure that the opening being added or updated is
 * unique in terms of identity in the UniqueOpeningList. However, the removal of a opening
 * uses Opening#equals(Object) so as to ensure that the opening
 * with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Opening#isSameOpening(Opening)
 */
public class UniqueOpeningList implements Iterable<Opening> {

    private final ObservableList<Opening> internalList = FXCollections.observableArrayList();
    private final ObservableList<Opening> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent opening as the given argument.
     */
    public boolean contains(Opening toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOpening);
    }

    /**
     * Adds a opening to the list.
     * The opening must not already exist in the list.
     */
    public void add(Opening toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOpeningException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the opening {@code target} in the list with {@code editedOpening}.
     * {@code target} must exist in the list.
     * The opening identity of {@code editedOpening} must not be the same as another existing opening in the list.
     */
    public void setOpening(Opening target, Opening editedOpening) {
        requireAllNonNull(target, editedOpening);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OpeningNotFoundException();
        }

        if (!target.isSameOpening(editedOpening) && contains(editedOpening)) {
            throw new DuplicateOpeningException();
        }

        internalList.set(index, editedOpening);
    }

    /**
     * Removes the equivalent opening from the list.
     * The opening must exist in the list.
     */
    public void remove(Opening toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OpeningNotFoundException();
        }
    }

    public void setOpenings(UniqueOpeningList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code openings}.
     * {@code openings} must not contain duplicate openings.
     */
    public void setOpenings(List<Opening> openings) {
        requireAllNonNull(openings);
        if (!openingsAreUnique(openings)) {
            throw new DuplicateOpeningException();
        }
        internalList.setAll(openings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Opening> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Opening> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOpeningList // instanceof handles nulls
                        && internalList.equals(((UniqueOpeningList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code openings} contains only unique openings.
     */
    private boolean openingsAreUnique(List<Opening> openings) {
        for (int i = 0; i < openings.size() - 1; i++) {
            for (int j = i + 1; j < openings.size(); j++) {
                if (openings.get(i).isSameOpening(openings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
