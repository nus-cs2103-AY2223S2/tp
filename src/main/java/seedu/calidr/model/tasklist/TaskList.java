package seedu.calidr.model.tasklist;

import static java.util.Objects.requireNonNull;
import static javafx.collections.FXCollections.observableList;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.calidr.commons.core.index.Index;
import seedu.calidr.model.ReadOnlyTaskList;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.params.Priority;

/**
 * Represents a task list manager that aids in storing and manipulating the
 * list of Tasks.
 */
public class TaskList implements ReadOnlyTaskList {

    private List<Task> tasks = new ArrayList<>();

    private void markUnmark(Index taskNumber, boolean isMarkCommand) {

        if (isMarkCommand) {
            this.tasks.get(taskNumber.getZeroBased()).mark();
        } else {
            this.tasks.get(taskNumber.getZeroBased()).unmark();
        }

    }

    /**
     * Marks a particular Task in the list of Tasks, as done.
     *
     * @param taskNumber The number to indicate which Task is to be marked as done.
     */
    public void markTask(Index taskNumber) {
        this.markUnmark(taskNumber, true);
    }

    /**
     * Marks a particular Task in the list of Tasks, as undone.
     *
     * @param taskNumber The number to indicate which Task is to be marked as undone.
     */
    public void unmarkTask(Index taskNumber) {
        this.markUnmark(taskNumber, false);
    }

    /**
     * Adds a given Task to the list of Tasks.
     *
     * @param task The Task to be added to the list of Tasks.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task from the list of Tasks.
     *
     * @param taskNumber The number to indicate which Task is to be deleted.
     */
    public void deleteTask(Index taskNumber) {
        this.tasks.remove(taskNumber.getZeroBased());
    }

    public void setTaskPriority(Index taskNumber, Priority priority) {
        this.tasks.get(taskNumber.getZeroBased()).setPriority(priority);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return observableList(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     * @param newData The new data to update the task list.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        tasks = newData.getTaskList();
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     * @param task The task to check.
     * @return true if task already exists in the task list and false otherwise.
     */
    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }

    public void setTask(Index targetIndex, Task editedTask) {
        tasks.add(targetIndex.getZeroBased(), editedTask);
    }
}
