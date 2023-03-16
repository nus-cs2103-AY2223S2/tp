package seedu.sudohr.model.leave;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.sudohr.model.leave.exceptions.DuplicateLeaveException;
import seedu.sudohr.model.leave.exceptions.LeaveNotFoundException;
import seedu.sudohr.model.person.exceptions.DuplicatePersonException;

/**
 * A list of leaves that enforces uniqueness between its elements and does not
 * allow nulls.
 * A leave is considered unique by comparing using
 * {@code Leave#isSameLeave(Leave)}. As such,
 * adding and updating of
 * leaves uses Leave#isSameLeave(Leave) for equality so as to ensure that the
 * leave being added
 * or updated is
 * unique in terms of identity in the UniqueLeaveList. However, the removal of a
 * leave uses
 * Leaves#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueLeaveList implements Iterable<Leave> {
    private final ObservableList<Leave> internalList = FXCollections.observableArrayList();
    private final ObservableList<Leave> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent leave as the given argument.
     */
    public boolean contains(Leave toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLeave);
    }

    /**
     * Adds a leave to the list.
     * The leave must not already exist in the list.
     */
    public void addLeave(Leave toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLeaveException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the leave {@code target} in the list with {@code editedLeave}.
     * {@code target} must exist in the list.
     * The leave identity of {@code editedPerson} must not be the same as another
     * existing person in the list.
     */
    public void setLeave(Leave target, Leave editedLeave) {
        requireAllNonNull(target, editedLeave);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LeaveNotFoundException();
        }

        if (!target.isSameLeave(editedLeave) && contains(editedLeave)) {
            throw new DuplicateLeaveException();
        }

        internalList.set(index, editedLeave);
    }

    /**
     * Removes the equivalent leave from the list.
     * The leave must exist in the list.
     */
    public void remove(Leave toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LeaveNotFoundException();
        }
    }

    public void setLeaves(UniqueLeaveList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setLeaves(List<Leave> leaves) {
        requireAllNonNull(leaves);
        if (!leavesAreUnique(leaves)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(leaves);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Leave> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Leave> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLeaveList // instanceof handles nulls
                        && internalList.equals(((UniqueLeaveList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean leavesAreUnique(List<Leave> leaves) {
        for (int i = 0; i < leaves.size() - 1; i++) {
            for (int j = i + 1; j < leaves.size(); j++) {
                if (leaves.get(i).isSameLeave(leaves.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
