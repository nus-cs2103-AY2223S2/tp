package vimification.model;

import javafx.collections.ObservableList;
import vimification.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTaskPlanner {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();

}
