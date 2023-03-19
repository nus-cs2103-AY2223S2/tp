package vimification.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import vimification.model.task.Task;

/**
 * Responsible for storing, retrieving and updating all the tasks that are currently on the list.
 */
public class LogicTaskList {

    private final List<Task> tasks;

    public LogicTaskList() {
        this.tasks = new ArrayList<>();
    }

    public LogicTaskList(List<Task> list) {
        this.tasks = list;
    }

    public LogicTaskList(Task[] arr) {
        this.tasks = Arrays.asList(arr);
    }

    public int length() {
        return tasks.size();
    }

    //// Task-level operations

    /**
     * Returns the task with the specified index.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Returns true if a task that is the same as {@code t} exists in the task list.
     */
    public boolean has(Task t) {
        requireNonNull(t);
        for (Task task : tasks) {
            if (t.isSameTask(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a task to the task list.
     */
    public void add(Task t) {
        requireNonNull(t);
        tasks.add(t);
    }

    /**
     * Inserts a task to the task list at the specified index.
     */
    public void insert(int idx, Task t) {
        requireNonNull(t);
        tasks.add(idx, t);
    }

    /**
     * Removes the task with the specified index from the task list.
     */
    public Task delete(int idx) throws IndexOutOfBoundsException {
        return tasks.remove(idx);
    }

    /**
     * Removes the last task from the task list.
     */
    public void pop() {
        delete(length() - 1);
    }

    /**
     * Marks the task with the specified index as done.
     */
    public void markTask(int idx) {
        get(idx).mark();
    }

    /**
     * Unmarks the task with the specified index as not done.
     */
    public void unmarkTask(int idx) {
        get(idx).unmark();
    }

    /**
     * Replaces the task with the specified index with the given task.
     */
    public void set(int idx, Task newTask) {
        requireNonNull(newTask);
        tasks.set(idx, newTask);
    }

    /**
     * Returns the index of the task with the specified index.
     */
    public int indexOf(Task t) {
        return tasks.indexOf(t);
    }

    //// util methods

    public LogicTaskList slice(int start, int end) {
        return new LogicTaskList(tasks.subList(start, end));
    }

    public LogicTaskList filter(Predicate<Task> pred) {
        LogicTaskList result = new LogicTaskList();
        for (Task t : tasks) {
            if (pred.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < length(); i++) {
            String prefix = i + 1 < 10 ? "0" : "";
            result += prefix + (i + 1) + ". " + get(i).toString();
            if (i < length() - 1) {
                result += "\n";
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogicTaskList // instanceof handles nulls
                        && tasks.equals(((LogicTaskList) other).tasks));
    }
}
