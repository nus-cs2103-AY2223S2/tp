package vimification.model;

import java.util.List;

import vimification.model.task.Task;

/**
 * Instances of this interface are responsible for storing, retrieving and updating {@link Task}
 * instances in the application.
 * <p>
 * Methods of this interface that expect an index should use the index as an index on the wrapped
 * task list.
 */
public interface LogicTaskList {

    /**
     * Returns the current size of the task list.
     *
     * @return the current size of the task list
     */
    public int size();

    /**
     * Checks whether the task list is empty or not.
     *
     * @return true if the task list is empty, otherwise false
     */
    public boolean isEmpty();

    /**
     * Returns the task with the specified index.
     *
     * @param index index of the task
     * @return the task at the specified index
     */
    public Task get(int index);

    /**
     * Removes the task with the specified index from the task list.
     *
     * @param index index of the task
     * @return the task at the specified index (before removal)
     */
    public Task remove(int index);

    /**
     * Replaces the task at the specified index with the given task.
     *
     * @param index index of the task
     * @param newTask the new task to replace the old one
     */
    public void set(int index, Task newTask);

    /**
     * Adds a task to the task list.
     *
     * @param task the new task to be added
     */
    public void add(Task task);

    /**
     * Adds a task to the task list at the specified index.
     *
     * @param index the index to insert the task into
     * @param task the new task to be inserted into the task list
     */
    public void add(int index, Task task);

    /**
     * Removes the last task from the task list.
     *
     * @return the last task in the task list
     */
    public Task removeLast();

    /**
     * Returns the actual index of the task at the specified index, in the source task list.
     * <p>
     * Note that, task index with respect to this instance may not be the actual index of the task
     * in the original source. Retrieving the actual index in the original source is necessary
     * before calling other methods.
     *
     * @param index index of the task, with respect to this instance
     * @return the actual index of the task in the source
     */
    public int getLogicSourceIndex(int index);

    /**
     * Returns an unmodifiable view of the source.
     *
     * @return an unmodifiable view of the source
     */
    public List<Task> getLogicSource();
}
