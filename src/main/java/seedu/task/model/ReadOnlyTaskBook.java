package seedu.task.model;

import javafx.collections.ObservableList;
import seedu.task.model.task.Task;

/**
 * Unmodifiable view of a task book
 */
public interface ReadOnlyTaskBook {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
