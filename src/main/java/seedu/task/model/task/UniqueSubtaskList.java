package seedu.task.model.task;

import static java.util.Objects.requireNonNull;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.task.model.task.exceptions.DuplicateTaskException;
import seedu.task.model.task.exceptions.TaskNotFoundException;

public class UniqueSubtaskList implements Iterable<Subtask> {

    protected final ObservableList<Subtask> uniqueList = FXCollections.observableArrayList();
    protected final ObservableList<Subtask> internalUnmodifiableList = FXCollections.unmodifiableObservableList(uniqueList);

    /**
     * Adds a subtask to the list
     * @param add The subtask to be added
     */
    public void addSubtask(Subtask add) {
        requireNonNull(add);
        if (contains(add)) {
            throw new DuplicateTaskException();
        }
        uniqueList.add(add);
    }

    /**
     * Checks whether the list contains the subtask
     * @param check The input to be checked against the list
     * @return True or False
     */
    public boolean contains(Subtask check) {
        requireNonNull(check);
        return uniqueList.stream().anyMatch(check::isSameTask);
    }

    /**
     * Remove the subtask from the list
     * @param toRemove The subtask to be removed
     */
    public void remove(Subtask toRemove) {
        requireNonNull(toRemove);
        if (!uniqueList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Gets subtask from the list based on an index
     * @param index The index of the subtask in the list
     * @return A subtask
     */
    public Subtask getSubtask(int index) {
        return uniqueList.get(index);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return Observable list
     */
    public ObservableList<Subtask> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Hash the list
     * @return A hash value
     */
    public int hashCode() {
        return uniqueList.hashCode();
    }

    public int size() {
        return this.uniqueList.size();
    }

    @Override
    public Iterator<Subtask> iterator() {
        return uniqueList.iterator();
    }


    public boolean equals(Object other) {
        return other == this || (other instanceof UniqueSubtaskList && uniqueList.equals(((UniqueSubtaskList) other).uniqueList));
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Subtask> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
