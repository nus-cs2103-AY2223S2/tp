package seedu.fitbook.model.routines;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fitbook.model.routines.exceptions.DuplicateRoutineException;
import seedu.fitbook.model.routines.exceptions.RoutineNotFoundException;

/**
 * A list of routines that enforces uniqueness between its elements and does not allow nulls.
 * A routine is considered unique by comparing using {@code Routine#isSameRoutine(Routine)}.
 * As such, adding and updating of
 * routines uses Routine#isSameRoutine(Routine) for equality so as to ensure that the routine being added or updated is
 * unique in terms of identity in the UniqueRoutineList. However, the removal of a routine uses Routine#equals(Object)
 * so as to ensure that the routine with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Routine#isSameRoutine(Routine)
 */
public class UniqueRoutineList implements Iterable<Routine> {

    private final ObservableList<Routine> internalList = FXCollections.observableArrayList();
    private final ObservableList<Routine> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent routine as the given argument.
     */
    public boolean contains(Routine toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoutine);
    }

    /**
     * Adds a routine to the list.
     * The routine must not already exist in the list.
     */
    public void add(Routine toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoutineException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the routine {@code target} in the list with {@code editedRoutine}.
     * {@code target} must exist in the list.
     * The routine identity of {@code editedRoutine} must not be the same as another existing routine in the list.
     */
    public void setRoutine(Routine target, Routine editedRoutine) {
        requireAllNonNull(target, editedRoutine);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RoutineNotFoundException();
        }

        if (!target.isSameRoutine(editedRoutine) && contains(editedRoutine)) {
            throw new DuplicateRoutineException();
        }

        internalList.set(index, editedRoutine);
    }

    /**
     * Removes the equivalent routine from the list.
     * The routine must exist in the list.
     */
    public void remove(Routine toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RoutineNotFoundException();
        }
    }

    public void setRoutines(UniqueRoutineList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code routines}.
     * {@code routines} must not contain duplicate routines.
     */
    public void setRoutines(List<Routine> routines) {
        requireAllNonNull(routines);
        if (!routinesAreUnique(routines)) {
            throw new DuplicateRoutineException();
        }

        internalList.setAll(routines);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Routine> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Routine> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRoutineList // instanceof handles nulls
                        && internalList.equals(((UniqueRoutineList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code routines} contains only unique routines.
     */
    private boolean routinesAreUnique(List<Routine> routines) {
        for (int i = 0; i < routines.size() - 1; i++) {
            for (int j = i + 1; j < routines.size(); j++) {
                if (routines.get(i).isSameRoutine(routines.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
