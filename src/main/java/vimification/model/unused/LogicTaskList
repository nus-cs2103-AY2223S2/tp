package vimification.model;

import java.util.stream.Stream;

import vimification.model.task.Task;

/**
 * Responsible for storing, retrieving and updating {@link Task} instances that are stored in the
 * list pointed to by the {@link TaskListRef}.
 * <p>
 * Trying to modify a reference that is currently in read-only mode will throw an exception.
 */
public class LogicTaskList {

    private TaskListRef ref;

    public LogicTaskList(TaskListRef ref) {
        this.ref = ref;
    }

    public int size() {
        return ref.getTaskList().size();
    }

    public boolean isEmpty() {
        return ref.getTaskList().isEmpty();
    }

    /**
     * Returns the task with the specified index.
     */
    public Task get(int index) {
        return ref.getTaskList().get(index);
    }

    /**
     * Adds a task to the task list.
     */
    public void add(Task task) {
        ref.getTaskList().add(task);
    }

    /**
     * Inserts a task to the task list at the specified index.
     */
    public void add(int index, Task task) {
        ref.getTaskList().add(index, task);
    }

    /**
     * Removes the task with the specified index from the task list.
     */
    public Task remove(int index) {
        return ref.getTaskList().remove(index);
    }

    /**
     * Replaces the task with the specified index with the given task.
     */
    public void set(int index, Task newTask) {
        ref.getTaskList().set(index, newTask);
    }

    /**
     * Returns the index of the task with the specified index.
     */
    public int indexOf(Task task) {
        return ref.getTaskList().indexOf(task);
    }

    /**
     * Returns true if a task that is the same as {@code t} exists in the task list.
     */
    public boolean contains(Task task) {
        return ref.getTaskList().contains(task);
    }

    public Stream<Task> stream() {
        return ref.getTaskList().stream();
    }

    /**
     * Removes the last task from the task list.
     */
    public void pop() {
        ref.getTaskList().remove(size() - 1);
    }

    public void clear() {
        ref.getTaskList().clear();
    }
}
