package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import tfifteenfour.clipboard.model.UniqueList;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateGroupException;
import tfifteenfour.clipboard.model.course.exceptions.GroupNotFoundException;

/**
 * A list of groups that enforces uniqueness between its elements and does not allow nulls.
 * A group is considered unique by comparing using {@code Group#isSameGroup(Group)}. As such,
 * adding and updating of groups uses Group#isSameGroup(Group) for equality so as to ensure that
 * the group being added or updated is unique in terms of identity in the UniqueGroupsList. However,
 * the removal of a group uses Group#equals(Object) so as to ensure that the group with exactly
 * the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Group#isSameGroup(Group)
 */
public class UniqueGroupsList extends UniqueList<Group> {

    @Override
    public UniqueGroupsList copy() {
        UniqueGroupsList copy = new UniqueGroupsList();
        this.internalList.forEach(group -> copy.add(group.copy()));

        return copy;
    }

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGroup);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    @Override
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedGroup} must not be the same as another existing student in the list.
     */
    @Override
    public void set(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GroupNotFoundException();
        }

        if (!target.isSameGroup(editedGroup) && contains(editedGroup)) {
            throw new DuplicateGroupException();
        }

        internalList.set(index, editedGroup);
    }

    @Override
    public Iterator<Group> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGroupsList // instanceof handles nulls
                        && internalList.equals(((UniqueGroupsList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code groups} contains only unique groups.
     */
    protected boolean elementsAreUnique(List<Group> groups) {
        for (int i = 0; i < groups.size() - 1; i++) {
            for (int j = i + 1; j < groups.size(); j++) {
                if (groups.get(i).isSameGroup(groups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
