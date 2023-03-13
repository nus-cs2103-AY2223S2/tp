package vimification.model;

import javafx.collections.ObservableList;
import vimification.model.person.Person;
import vimification.model.task.Task;

/**
 * Unmodifiable view of a task list
 */
public interface ViewList {

    /**
     * Returns an unmodifiable view of the task list.
     */
    ObservableList<Task> getViewOnlyTaskList();
}
