package seedu.address.model.tutee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutee.exceptions.DuplicatePersonException;
import seedu.address.model.tutee.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A tutee is considered unique by comparing using {@code Tutee#isSamePerson(Tutee)}. As such, adding and updating of
 * persons uses Tutee#isSamePerson(Tutee) for equality so as to ensure that the tutee being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a tutee uses Tutee#equals(Object) so
 * as to ensure that the tutee with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tutee#isSamePerson(Tutee)
 */
public class UniquePersonList implements Iterable<Tutee> {

    private final ObservableList<Tutee> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutee> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutee as the given argument.
     */
    public boolean contains(Tutee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a tutee to the list.
     * The tutee must not already exist in the list.
     */
    public void add(Tutee toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tutee {@code target} in the list with {@code editedTutee}.
     * {@code target} must exist in the list.
     * The tutee identity of {@code editedTutee} must not be the same as another existing tutee in the list.
     */
    public void setPerson(Tutee target, Tutee editedTutee) {
        requireAllNonNull(target, editedTutee);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedTutee) && contains(editedTutee)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTutee);
    }

    /**
     * Removes the equivalent tutee from the list.
     * The tutee must exist in the list.
     */
    public void remove(Tutee toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutees}.
     * {@code tutees} must not contain duplicate tutees.
     */
    public void setPersons(List<Tutee> tutees) {
        requireAllNonNull(tutees);
        if (!personsAreUnique(tutees)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(tutees);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tutee> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tutee> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tutees} contains only unique tutees.
     */
    private boolean personsAreUnique(List<Tutee> tutees) {
        for (int i = 0; i < tutees.size() - 1; i++) {
            for (int j = i + 1; j < tutees.size(); j++) {
                if (tutees.get(i).isSamePerson(tutees.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
