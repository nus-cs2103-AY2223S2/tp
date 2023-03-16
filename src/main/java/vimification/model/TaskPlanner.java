package vimification.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import vimification.model.task.Task;
import vimification.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TaskPlanner implements ReadOnlyTaskPlanner {

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

    public TaskPlanner() {}

    /**
     * Creates an TaskPlanner using the Persons in the {@code toBeCopied}
     */
    public TaskPlanner(ReadOnlyTaskPlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskPlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskPlanner newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTask(Task t) {
        requireNonNull(t);
        return tasks.contains(t);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    public void markTask(Task t) { tasks.mark(t);}

    public void unmarkTask(Task t) { tasks.unmark(t);}

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTasks(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskPlanner}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskPlanner // instanceof handles nulls
                && tasks.equals(((TaskPlanner) other).tasks));
    }
}
