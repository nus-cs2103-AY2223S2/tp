package vimification.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

/**
 * Responsible for storing, retrieving and updating all the tasks that are currently on the list.
 */
public class LogicTaskList {

    private List<Task> tasks;

    public LogicTaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public LogicTaskList() {
        this(new ArrayList<>());
    }

    public LogicTaskList(Task... tasks) {
        this(new ArrayList<>(Arrays.asList(tasks)));
    }

    public List<Task> getInternalList() {
        return tasks;
    }

    public void setInternalList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    //// Task-level operations

    /**
     * Returns the task with the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     */
    public void add(Task task) {
        requireNonNull(task);
        tasks.add(task);
    }

    /**
     * Inserts a task to the task list at the specified index.
     */
    public void add(int index, Task task) {
        requireNonNull(task);
        tasks.add(index, task);
    }

    /**
     * Removes the task with the specified index from the task list.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Replaces the task with the specified index with the given task.
     */
    public void set(int index, Task newTask) {
        requireNonNull(newTask);
        tasks.set(index, newTask);
    }

    /**
     * Returns the index of the task with the specified index.
     */
    public int indexOf(Task t) {
        return tasks.indexOf(t);
    }

    /**
     * Returns true if a task that is the same as {@code t} exists in the task list.
     */
    public boolean contains(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }

    /**
     * Removes the last task from the task list.
     */
    public void pop() {
        tasks.remove(size() - 1);
    }

    /**
     * Marks the task with the specified index as done.
     */
    /**
     * public void mark(int index) { tasks.get(index).mark(); }
     **/

    /**
     * Unmarks the task with the specified index as not done.
     */
    /**
     * public void unmark(int index) { tasks.get(index).unmark(); }
     **/

    public Priority getPriority(int index) {
        return tasks.get(index).getPriority();
    }

    public void setPriority(int index, Priority newPriority) {
        tasks.get(index).setPriority(newPriority);
    }

    public String getTitle(int index) {
        return tasks.get(index).getTitle();
    }

    public void setTitle(int index, String newDescription) {
        tasks.get(index).setTitle(newDescription);
    }

    public Status getStatus(int index) {
        return tasks.get(index).getStatus();
    }

    public void setStatus(int index, Status newStatue) {
        tasks.get(index).setStatus(newStatue);
    }

    public LocalDateTime getDeadline(int index) {
        return tasks.get(index).getDeadline();
    }

    public void setDeadline(int index, LocalDateTime newDate) {
        tasks.get(index).setDeadline(newDate);
    }

    public void deleteDeadline(int index) {
        tasks.get(index).deleteDeadline();
    }

    /**
     * public List<Task> removeAllDone() { Predicate<Task> pred = Task::isDone; List<Task> tasksDone
     * = stream() .filter(pred) .collect(Collectors.toCollection(ArrayList::new));
     * tasks.removeAll(tasksDone); return tasksDone; }
     */

    public void addAll(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void clear() {
        this.tasks.clear();
    }

    //// util methods

    public LogicTaskList slice(int start, int end) {
        return new LogicTaskList(tasks.subList(start, end));
    }

    /**
     * Filter {@code LogicTaskList} based on predicate.
     *
     * @param pred
     * @return
     */
    public List<Task> filter(Predicate<Task> pred) {
        List<Task> filteredTasks = stream()
                .filter(pred)
                .collect(Collectors.toCollection(ArrayList::new));
        return filteredTasks;
    }

    @Override
    public String toString() {
        // TODO: rewrite this, too confusing
        String result = "";
        for (int i = 0; i < size(); i++) {
            String prefix = i + 1 < 10 ? "0" : "";
            result += prefix + (i + 1) + ". " + get(i).toString();
            if (i < size() - 1) {
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
