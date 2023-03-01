package seedu.address.model;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the product level
 * Duplicates are not allowed (by .isSameFish comparison) <- to be confirmed
 */
public class TaskList implements ReadOnlyTaskList {
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

    public TaskList() {}

    /**
     * Creates a {@code TaskList} using the {@code Task}s in the {@code toBeCopied}.
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the {@code TaskList} with {@code Tasks}.
     * {@code Tasks} must not contain duplicate {@code Task}s.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code SoConnect} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// Task-level operations

    /**
     * Returns true if a {@code Task} identical to {@code Task} exists in the {@code TaskList}.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a {@code Task} to the {@code TaskList}.
     * The {@code Task} must not already exist in the {@code TaskList}.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Replaces the given {@code Task} in the {@code TaskList} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes a {@code Task} from this {@code TaskList}.
     * The {@code Task} must exist in the {@code TaskList}.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && tasks.equals(((TaskList) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
