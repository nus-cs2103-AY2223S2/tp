package vimification.model;

import java.util.List;

import vimification.model.task.Task;

/**
 * Instances of this class are responsible for storing, retrieving and updating {@link Task}
 * instances that are stored in the application.
 */
public interface LogicTaskList1 {

    /**
     * Returns the task with the specified index.
     *
     * @param index index of the task
     * @return the task at the specified index
     */
    public Task get(int index);

    /**
     * Removes the task with the specified index from the task list.
     */
    public Task remove(int index);

    /**
     * Replaces the task with the specified index with the given task.
     */
    public void set(int index, Task newTask);

    /**
     * Adds a task to the task list.
     */
    public void add(Task task);

    /**
     * Removes the last task from the task list.
     */
    public Task pop();

    public List<Task> getLogicSource();
}
