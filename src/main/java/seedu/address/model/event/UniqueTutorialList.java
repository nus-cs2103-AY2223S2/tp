package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Tutorial;
import seedu.address.model.event.exceptions.DuplicateTutorialException;
import seedu.address.model.event.exceptions.TutorialNotFoundException;

public class UniqueTutorialList implements Iterable<Tutorial> {

    private final ObservableList<Tutorial> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutorial> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Tutorial toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorial);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Tutorial toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedTutorial}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedTutorial} must not be the same as another existing person in the list.
     */
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireAllNonNull(target, editedTutorial);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorialNotFoundException();
        }

        if (!target.isSameTutorial(editedTutorial) && contains(editedTutorial)) {
            throw new DuplicateTutorialException();
        }

        internalList.set(index, editedTutorial);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Tutorial toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialNotFoundException();
        }
    }

    public void setTutorials(UniqueTutorialList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTutorials(List<Tutorial> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicateTutorialException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tutorial> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tutorial> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorialList // instanceof handles nulls
                && internalList.equals(((UniqueTutorialList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Tutorial> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameTutorial(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
