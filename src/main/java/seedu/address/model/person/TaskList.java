package seedu.address.model.person;

import seedu.address.model.person.exceptions.TaskNotFoundException;

import java.util.ArrayList;

/**
 * Represents a Person's Tasklist in the address book.
 * Guarantees: immutable, is always valid
 */
public class TaskList {
    static final String EMPTY_TASKLIST_MESSAGE = "The Tasklist for this individual is empty.";

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a clone of the tasklist, guarantees immutability
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            newTasks.add(task);
        }
        return newTasks;
    }

    public Task get(int index) throws TaskNotFoundException {
        try {
            return get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public TaskList add(Task task) {
        ArrayList<Task> newTasks = getTaskList();
        newTasks.add(task);
        return new TaskList(newTasks);
    }

    public int size() {
        return tasks.size();
    }

    public TaskList clear() {
        return new TaskList();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return EMPTY_TASKLIST_MESSAGE;
        }
        String returnString = "Tasks:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            returnString += String.format("%d. %s\n", i, this.get(i));
        }
        return returnString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskList)) {
            return false;
        }

        assert other instanceof TaskList;

        TaskList otherTasks = (TaskList) other;

        if (tasks.size() != otherTasks.size()) {
            return false;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            Task originalTask = tasks.get(i);
            Task otherTask = otherTasks.get(i);
            if (!originalTask.equals(otherTask)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
