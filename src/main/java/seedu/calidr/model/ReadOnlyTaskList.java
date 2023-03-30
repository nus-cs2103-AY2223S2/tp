package seedu.calidr.model;

import javafx.collections.ObservableList;
import seedu.calidr.model.task.Task;

/**
 * Unmodifiable view of a task list.
 */
public interface ReadOnlyTaskList {

    /**
     * Returns an unmodifiable view of the task list.
     */
    ObservableList<Task> getTaskList();
}
