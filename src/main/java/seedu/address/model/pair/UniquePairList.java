package seedu.address.model.pair;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pair.exceptions.DuplicatePairException;
import seedu.address.model.pair.exceptions.PairNotFoundException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * A list of pairs that enforces uniqueness between its elements and does not allow nulls.
 * A pair is considered unique by comparing using {@code Pair#isSamePair(Pair)}. As such, adding and updating of
 * pairs uses Pair#isSamePair(Pair) for equality so as to ensure that the pair being added or updated is
 * unique in terms of identity in the UniquePairList. However, the removal of a pair uses Pair#equals(Object) so
 * as to ensure that the pair with exactly the same fields will be removed.
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
     *
     * @param toCheck Pair to check in pair list.
     * @return True if pair exists in the pair list, false otherwise.
     */
    public boolean contains(Pair toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePair);
    }

    /**
     * Adds a pair to the list.
     * The pair must not already exist in the list.
     *
     * @param toAdd Pair to be added into FriendlyLink.
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
     *
     * @param target Pair to edit.
     * @param editedPair Pair with edited information.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireAllNonNull(target, editedPair);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PairNotFoundException();
        }
        assert index < internalList.size() : "Index should be in range";

        if (!target.isSamePair(editedPair) && contains(editedPair)) {
            throw new DuplicatePairException();
        }

        internalList.set(index, editedPair);
    }

    /**
     * Removes the equivalent pair from the list.
     * The pair must exist in the list.
     *
     * @param toRemove Pair to remove.
     */
    public void remove(Pair toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PairNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     *
     * @param replacement Replacement pair list.
     */
    public void setPairs(UniquePairList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code pairs}.
     * {@code pairs} must not contain duplicate pairs.
     *
     * @param pairs Replacement pair list.
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
     *
     * @return Unmodifiable pair list.
     */
    public ObservableList<Pair> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Gets list of volunteers paired with a specified elderly.
     * {@code elderlyNric} must not be null.
     *
     * @param elderlyNric Nric of the specified elderly
     * @return List of volunteers paired with the specified elderly.
     */
    public List<Volunteer> getPairedVolunteers(Nric elderlyNric) {
        requireNonNull(elderlyNric);
        ArrayList<Volunteer> results = new ArrayList<>();
        for (Pair pair : this) {
            if (pair.getElderly().getNric().equals(elderlyNric)) {
                results.add(pair.getVolunteer());
            }
        }
        return results;
    }

    /**
     * Gets list of elderly paired with a specified volunteer.
     * Returns the full list of elderly if {@code volunteerNric} is null.
     *
     * @param volunteerNric Nric of the specified volunteer
     * @return List of elderly paired with the specified volunteer.
     */
    public List<Elderly> getPairedElderly(Nric volunteerNric) {
        requireNonNull(volunteerNric);
        ArrayList<Elderly> results = new ArrayList<>();
        for (Pair pair : this) {
            if (pair.getVolunteer().getNric().equals(volunteerNric)) {
                results.add(pair.getElderly());
            }
        }
        return results;

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
     *
     * @param pairs List of pairs to check.
     * @return True if {@code pairs} contains only unique pairs and false otherwise.
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
