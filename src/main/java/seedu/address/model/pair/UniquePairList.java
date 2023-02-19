package seedu.address.model.pair;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pair.exceptions.DuplicatePairException;
import seedu.address.model.pair.exceptions.PairNotFoundException;

/**
 * A list of pairs that enforces uniqueness between its elements and does not allow nulls.
 * A pair is considered unique by comparing using {@code Pair#isSamePair(Pair)}. As such, adding and updating of
 * pairs uses Pair#isSamePair(Pair) for equality so as to ensure that the pair being added or updated is
 * unique in terms of identity in the UniquePairList. However, the removal of a person uses Pair#equals(Object) so
 * as to ensure that the pair with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Pair#isSamePair(Pair)
 */
public class UniquePairList implements Iterable<Pair> {

    private final ObservableList<Pair> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pair> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent pair as the given argument.
     */
    public boolean contains(Pair toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePair);
    }

    /**
     * Adds a pair to the list.
     * The pair must not already exist in the list.
     */
    public void add(Pair toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePairException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the pair {@code target} in the list with {@code editedPair}.
     * {@code target} must exist in the list.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in the list.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireAllNonNull(target, editedPair);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PairNotFoundException();
        }

        if (!target.isSamePair(editedPair) && contains(editedPair)) {
            throw new DuplicatePairException();
        }

        internalList.set(index, editedPair);
    }

    /**
     * Removes the equivalent pair from the list.
     * The pair must exist in the list.
     */
    public void remove(Pair toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PairNotFoundException();
        }
    }

    public void setPairs(UniquePairList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code pairs}.
     * {@code pairs} must not contain duplicate pairs.
     */
    public void setPairs(List<Pair> pairs) {
        requireAllNonNull(pairs);
        if (!pairsAreUnique(pairs)) {
            throw new DuplicatePairException();
        }

        internalList.setAll(pairs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Pair> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Pair> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePairList // instanceof handles nulls
                && internalList.equals(((UniquePairList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code pairs} contains only unique pairs.
     */
    private boolean pairsAreUnique(List<Pair> pairs) {
        for (int i = 0; i < pairs.size() - 1; i++) {
            for (int j = i + 1; j < pairs.size(); j++) {
                if (pairs.get(i).isSamePair(pairs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
