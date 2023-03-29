package seedu.address.model.tank;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tank.exceptions.DuplicateTankException;
import seedu.address.model.tank.exceptions.TankNotFoundException;

/**
 * A list of tanks that enforces uniqueness between its elements and does not allow nulls.
 * A tank is considered unique by comparing using {@code Tank#isSameTank(Tank)}. As such, adding and updating of
 * Tankes uses Tank#isSameTank(Tank) for equality so as to ensure that the tank being added or updated is
 * unique in terms of identity in the UniqueTankList. However, the removal of a tank uses Tank#equals(Object) so
 * as to ensure that the tank with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tank#isSameTank(Tank)
 */
public class UniqueTankList implements Iterable<Tank> {
    private final ObservableList<Tank> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tank> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tank as the given argument.
     */
    public boolean contains(Tank toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTank);
    }

    /**
     * Adds a tank to the list.
     * The tank must not already exist in the list.
     */
    public void add(Tank toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTankException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tank {@code target} in the list with {@code editedTank}.
     * {@code target} must exist in the list.
     * The tank identity of {@code editedTank} must not be the same as another existing tank in the list.
     */
    public void setTank(Tank target, Tank editedTank) {
        requireAllNonNull(target, editedTank);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TankNotFoundException();
        }

        if (!target.isSameTank(editedTank) && contains(editedTank)) {
            throw new DuplicateTankException();
        }

        internalList.set(index, editedTank);
    }

    /**
     * Removes the equivalent tank from the list.
     * The tank must exist in the list.
     */
    public void remove(Tank toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TankNotFoundException();
        }
    }

    public void setTanks(UniqueTankList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tanks}.
     * {@code tanks} must not contain duplicate tanks.
     */
    public void setTanks(List<Tank> tanks) {
        requireAllNonNull(tanks);
        if (!tanksAreUnique(tanks)) {
            throw new DuplicateTankException();
        }

        internalList.setAll(tanks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tank> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tank> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTankList // instanceof handles nulls
                && internalList.equals(((UniqueTankList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tank} contains only unique tank.
     */
    private boolean tanksAreUnique(List<Tank> tanks) {
        for (int i = 0; i < tanks.size() - 1; i++) {
            for (int j = i + 1; j < tanks.size(); j++) {
                if (tanks.get(i).isSameTank(tanks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
