package vimification.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.logic.commands.AddCommand;
import vimification.model.person.Person;
import vimification.model.person.UniquePersonList;
import vimification.model.person.exceptions.DuplicatePersonException;
import vimification.model.person.exceptions.PersonNotFoundException;
import vimification.model.task.Task;
import vimification.model.task.exceptions.TaskNotFoundException;
import static java.util.Objects.requireNonNull;

/**
 * A list that manages the tasks it has
 */
public class TaskList implements ViewList {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    @Override
    public ObservableList<Task> getViewOnlyTaskList() {
        return internalUnmodifiableList;
    }

    /**
     * Adds a task to the list.
     */
    public void add(Task newTask) {
        requireNonNull(newTask);
        internalList.add(newTask);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(List<Task> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement);
    }

    public Task get(int index) {
        return internalList.get(index);
    }

    public int size() {
        return internalList.size();
    }

    // Mainly for testing
    public boolean contains(Task task) {
        for (Task item : internalList) {
            if (item.equals(task)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && (this.hasSameContentNOrder((TaskList) other)));
    }

    //=========== HELPER FUNC =============================================================
    private boolean hasSameContentNOrder(TaskList another) {
        int n = this.size();
        if (n != another.size()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!this.get(i).equals(another.get(i))) {
                return false;
            }
        }
        return true;
    }

}
