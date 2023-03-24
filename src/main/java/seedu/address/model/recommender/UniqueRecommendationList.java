package seedu.address.model.recommender;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recommender.exceptions.DuplicateRecommendationException;
import seedu.address.model.recommender.exceptions.RecommendationNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Recommendation#isSameRecommendation(Recommendation)}. As such, adding and updating of
 * persons uses Recommendation#isSameRecommendation(Recommendation) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueRecommendationList. However, the removal of a person uses Recommendation#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Recommendation#isSameRecommendation(Recommendation)
 */
public class UniqueRecommendationList implements Iterable<Recommendation> {

    private final ObservableList<Recommendation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recommendation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Recommendation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecommendation);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Recommendation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRecommendationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedRecommendation}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedRecommendation} must not be the same as another existing person in the list.
     */
    public void setRecommendation(Recommendation target, Recommendation editedRecommendation) {
        requireAllNonNull(target, editedRecommendation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RecommendationNotFoundException();
        }

        if (!target.isSameRecommendation(editedRecommendation) && contains(editedRecommendation)) {
            throw new DuplicateRecommendationException();
        }

        internalList.set(index, editedRecommendation);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Recommendation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RecommendationNotFoundException();
        }
    }

    public void setRecommendations(UniqueRecommendationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setRecommendations(List<Recommendation> persons) {
        requireAllNonNull(persons);
        if (!recommendationsAreUnique(persons)) {
            throw new DuplicateRecommendationException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Recommendation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Recommendation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRecommendationList // instanceof handles nulls
                && internalList.equals(((UniqueRecommendationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean recommendationsAreUnique(List<Recommendation> recommendations) {
        for (int i = 0; i < recommendations.size() - 1; i++) {
            for (int j = i + 1; j < recommendations.size(); j++) {
                if (recommendations.get(i).isSameRecommendation(recommendations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
