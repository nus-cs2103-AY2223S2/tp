package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of groups that enforces uniqueness between its elements and does not allow nulls.
 * A group is considered unique by comparing using group name and object
 */
public class UniqueGroupList {

    private final Set<Group> groups = new HashSet<>();

    /**
     * Adds a group to the set.
     * The group must not already exist in the set.
     *
     * @param toAdd Group to add to set
     */
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (groups.contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        groups.add(toAdd);
    }

    /**
     * Delete the equivalent group from the set.
     * The group must exist in the set.
     *
     * @param toRemove Group to remove from set
     * @param persons Persons to remove group from
     */
    public void delete(Group toRemove, Set<Person> persons) {
        requireNonNull(toRemove);
        if (!groups.contains(toRemove)) {
            throw new GroupNotFoundException();
        }
        persons.forEach(person -> person.removeGroup(toRemove));
        groups.remove(toRemove);
    }
}


