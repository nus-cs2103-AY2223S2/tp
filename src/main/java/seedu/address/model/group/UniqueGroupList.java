package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueGroupList {

    private Set<Group> groups = new HashSet<>();

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

    public ArrayList<Group> asList

    public boolean contains(Group toCheck) {
        return groups.contains(toCheck);
    }


    @Override
    public int hashCode() {
        return groups.hashCode();
    }
}


