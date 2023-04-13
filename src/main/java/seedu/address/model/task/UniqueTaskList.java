package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;


/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * Tasks uses Task#isSameTask(Task) for equality so as to ensure that the task being added or updated is
 * unique in terms of identity in the UniqueTaskList. However, the removal of a Task uses Task#equals(Object) so
 * as to ensure that the Task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * @param replacement
     */
    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }


    /**
     * Replaces the contents of this list with {@code Tasks}.
     * {@code Tasks} must not contain duplicate Tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Removes the deleted person from all tasks.
     * @param personIndex
     */
    public void deletePersonFromTask(Index personIndex) {
        requireNonNull(personIndex);
        int index = personIndex.getZeroBased();

        for (int i = 0; i < internalList.size(); i++) {
            internalList.get(i).deletePersonFromTask(index);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.UniqueTaskList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.task.UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns if the task index is valid
     * @param taskIndex
     * @return
     */
    public boolean checkIndex(Index taskIndex) {
        return taskIndex.getZeroBased() < internalList.size();
    }

    /**
     * Assigns a task to a person
     * @param taskIndex
     * @param personIndex
     */
    public void assignTask(Index taskIndex, Index personIndex, Person personToAssign) {
        internalList.get(taskIndex.getZeroBased()).assignPerson(personIndex, personToAssign);
    }


    public void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex) {
        internalList.set(taskIndex.getZeroBased(), assignedTask);
    }

    /**
     * Replace the task with the marked task
     * @param taskToMark
     * @param markedTask
     * @param taskIndex
     */
    public void markTask(Task taskToMark, Task markedTask, Index taskIndex) {
        internalList.set(taskIndex.getZeroBased(), markedTask);
    }

    /**
     * Replace the task with the unmarked task
     * @param taskToUnmark
     * @param unmarkedTask
     * @param taskIndex
     */
    public void unmarkTask(Task taskToUnmark, Task unmarkedTask, Index taskIndex) {
        internalList.set(taskIndex.getZeroBased(), unmarkedTask);
    }

    /**
     * Replace the task with the commented task
     * @param taskToComment
     * @param commentedTask
     * @param taskIndex
     */
    public void commentOnTask(Task taskToComment, Task commentedTask, Index taskIndex) {
        internalList.set(taskIndex.getZeroBased(), commentedTask);
    }
}
