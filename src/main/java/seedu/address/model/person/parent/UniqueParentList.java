package seedu.address.model.person.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a parent list containing Parent objects of Student objects in the same class / cca.
 */
public class UniqueParentList implements Iterable<Parent> {
    private final ObservableList<Parent> internalList = FXCollections.observableArrayList();
    private final ObservableList<Parent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Parent toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Parent toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setParent(Parent target, Parent editedParent) {
        requireAllNonNull(target, editedParent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedParent) && contains(editedParent)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedParent);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Parent toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setParents(UniqueParentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setParents(List<Parent> parents) {
        requireAllNonNull(parents);
        if (!parentsAreUnique(parents)) {
            throw new DuplicatePersonException();
        }
        internalList.setAll(parents);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Parent> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Parent> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueParentList // instanceof handles nulls
                && internalList.equals(((UniqueParentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean parentsAreUnique(List<Parent> parents) {
        for (int i = 0; i < parents.size() - 1; i++) {
            for (int j = i + 1; j < parents.size(); j++) {
                if (parents.get(i).isSamePerson(parents.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
