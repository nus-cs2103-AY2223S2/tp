package seedu.task.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.task.model.task.exceptions.DuplicateTaskException;
import seedu.task.model.task.exceptions.TaskNotFoundException;

/**
 * A list of subtasks that enforces uniqueness between its elements and does not allow nulls.
 * A subtask is considered unique by comparing using {@code Subtask#isSameTask(Subtask)}.
 * As such, adding and updating of tasks uses Subtask#isSameTask(Subtask) for equality so
 * as to ensure that the task being added or updated is unique in terms of identity in the
 * UniqueSubtaskList. However, the removal of a task uses Subtask#equals(Object) so
 * as to ensure that the task with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Subtask#isSameTask(Subtask)
 */
public class UniqueSubtaskList implements Iterable<Subtask> {

    protected final ObservableList<Subtask> uniqueList = FXCollections.observableArrayList();
    protected final ObservableList<Subtask> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(uniqueList);

    /**
     * Adds a subtask to the list
     * @param add The subtask to be added
     */
    public void addSubtask(Subtask add) {
        requireNonNull(add);
        if (contains(add)) {
            throw new DuplicateTaskException();
        }
        uniqueList.add(add);
    }

    /**
     * Checks whether the list contains the subtask
     * @param check The input to be checked against the list
     * @return True or False
     */
    public boolean contains(Subtask check) {
        requireNonNull(check);
        return uniqueList.stream().anyMatch(check::isSameTask);
    }

    /**
     * Remove the subtask from the list
     * @param toRemove The subtask to be removed
     */
    public void remove(Subtask toRemove) {
        requireNonNull(toRemove);
        if (!uniqueList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Gets subtask from the list based on an index
     * @param index The index of the subtask in the list
     * @return A subtask
     */
    public Subtask getSubtask(int index) {
        return uniqueList.get(index);
    }

    public void setSubtasks(List<Subtask> subtasks) {
        requireNonNull(subtasks);
        if (!tasksAreUnique(subtasks)) {
            throw new DuplicateTaskException();
        }
        uniqueList.setAll(subtasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return Observable list
     */
    public ObservableList<Subtask> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<Subtask> asInternalList() {
        return uniqueList;
    }
    /**
     * Hash the list
     * @return A hash value
     */
    public int hashCode() {
        return uniqueList.hashCode();
    }

    /**
     * Checks the size of the {@code uniqueList}
     * @return Length of the list
     */
    public int size() {
        return this.uniqueList.size();
    }

    /**
     * Creates an iterator to make the list iterable
     * @return An iterator
     */
    @Override
    public Iterator<Subtask> iterator() {
        return uniqueList.iterator();
    }


    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof UniqueSubtaskList && uniqueList.equals(((UniqueSubtaskList) other).uniqueList));
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Subtask> subtasks) {
        for (int i = 0; i < subtasks.size() - 1; i++) {
            for (int j = i + 1; j < subtasks.size(); j++) {
                if (subtasks.get(i).isSameTask(subtasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
