package seedu.address.model.mapping;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.mapping.exceptions.DuplicatePersonTaskException;
import seedu.address.model.mapping.exceptions.PersonTaskNotFoundException;


/**
 * A list of person-task mapping that enforces uniqueness between its elements and does not allow nulls.
 * A person-task  is considered unique by comparing using {@code PersonTask#equals(Object)}.
 * Supports a minimal set of list operations.
 *
 */
public class UniquePersonTaskList implements Iterable<PersonTask> {

    private final ObservableList<PersonTask> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonTask> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person-task as the given argument.
     */
    public boolean contains(PersonTask toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person-task to the list.
     * The person-task must not already exist in the list.
     */
    public void add(PersonTask toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonTaskException();
        }
        internalList.add(toAdd);
    }


    /**
     * Removes the equivalent person-task  from the list.
     * The person-task  must exist in the list.
     */
    public void remove(PersonTask toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonTaskNotFoundException();
        }
    }

    public void setPersonTasks(UniquePersonTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code personTasks}.
     * {@code personTasks} must not contain duplicate personTasks.
     */
    public void setPersonTasks(List<PersonTask> personTasks) {
        requireAllNonNull(personTasks);
        if (!personTasksAreUnique(personTasks)) {
            throw new DuplicatePersonTaskException();
        }

        internalList.setAll(personTasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonTask> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonTask> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniquePersonTaskList // instanceof handles nulls
            && internalList.equals(((UniquePersonTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code personTasks} contains only unique personTasks.
     */
    private boolean personTasksAreUnique(List<PersonTask> personTasks) {
        for (int i = 0; i < personTasks.size() - 1; i++) {
            for (int j = i + 1; j < personTasks.size(); j++) {
                if (personTasks.get(i).equals(personTasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
