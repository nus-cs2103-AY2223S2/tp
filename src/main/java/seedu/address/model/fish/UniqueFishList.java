package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.fish.exceptions.DuplicateFishException;
import seedu.address.model.fish.exceptions.FishNotFoundException;

/**
 * A list of Fishes that enforces uniqueness between its elements and does not allow nulls.
 * A fish is considered unique by comparing using {@code Fish#isSameFish(Fish)}. As such, adding and updating of
 * Fishes uses Fish#isSameFish(Fish) for equality so as to ensure that the fish being added or updated is
 * unique in terms of identity in the UniqueFishList. However, the removal of a fish uses Fish#equals(Object) so
 * as to ensure that the fish with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Fish#isSameFish(Fish)
 */
public class UniqueFishList implements Iterable<Fish> {

    private final ObservableList<Fish> internalList = FXCollections.observableArrayList();
    private final ObservableList<Fish> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent fish as the given argument.
     */
    public boolean contains(Fish toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFish);
    }

    /**
     * Adds a fish to the list.
     * The fish must not already exist in the list.
     */
    public void add(Fish toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFishException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the fish {@code target} in the list with {@code editedFish}.
     * {@code target} must exist in the list.
     * The fish identity of {@code editedFish} must not be the same as another existing fish in the list.
     */
    public void setFish(Fish target, Fish editedFish) {
        requireAllNonNull(target, editedFish);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FishNotFoundException();
        }

        if (!target.isSameFish(editedFish) && contains(editedFish)) {
            throw new DuplicateFishException();
        }

        internalList.set(index, editedFish);
    }

    /**
     * Removes the equivalent fish from the list.
     * The fish must exist in the list.
     */
    public void remove(Fish toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FishNotFoundException();
        }
    }

    public void setFishes(UniqueFishList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code fish}.
     * {@code fish} must not contain duplicate fish.
     */
    public void setFishes(List<Fish> fish) {
        requireAllNonNull(fish);
        if (!fishesAreUnique(fish)) {
            throw new DuplicateFishException();
        }

        internalList.setAll(fish);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Fish> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Fish> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFishList // instanceof handles nulls
                        && internalList.equals(((UniqueFishList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code fish} contains only unique fish.
     */
    private boolean fishesAreUnique(List<Fish> fish) {
        for (int i = 0; i < fish.size() - 1; i++) {
            for (int j = i + 1; j < fish.size(); j++) {
                if (fish.get(i).isSameFish(fish.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
