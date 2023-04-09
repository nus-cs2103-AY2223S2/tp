package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TaskBook implements ReadOnlyTaskBook {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public TaskBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public TaskBook(ReadOnlyTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskBook newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// person-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if a task with the same index as {@code taskIndex} exists in the address book.
     * @param taskIndex
     * @return
     */
    public boolean hasTaskIndex(Index taskIndex) {
        requireNonNull(taskIndex);
        return tasks.checkIndex(taskIndex);
    }


    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Marks the given task {@code task} as done.
     * @param taskToMark
     * @param markedTask
     * @param taskIndex
     */
    public void markTask(Task taskToMark, Task markedTask, Index taskIndex) {
        tasks.markTask(taskToMark, markedTask, taskIndex);
    }

    /**
     * Unmarks the given task {@code task} as not done.
     */
    public void unmarkTask(Task taskToUnmark, Task unmarkedTask, Index taskIndex) {
        tasks.unmarkTask(taskToUnmark, unmarkedTask, taskIndex);
    }

    /**
     * Adds given comment to the specified task.
     */
    public void commentOnTask(Task taskToComment, Task commentedTask, Index taskIndex) {
        tasks.commentOnTask(taskToComment, commentedTask, taskIndex);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Assigns the given task {@code taskToAssign} to the given task {@code assignedTask}.
     * @param taskToAssign
     * @param assignedTask
     * @param taskIndex
     */
    public void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex) {
        tasks.assignTask(taskToAssign, assignedTask, taskIndex);
    }

    /**
     * Removes the given person {@code person} from the assigned tasks {@code task}.
     * @param personIndex
     */
    public void deletePersonFromTask(Index personIndex) {
        tasks.deletePersonFromTask(personIndex);
    }


    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods


    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBook // instanceof handles nulls
                && tasks.equals(((TaskBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
