package seedu.address.model.tank.readings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tank.readings.exceptions.DuplicateReadingException;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of Ammonia Readings
 *
 * Supports a minimal set of list operations.
 */
public class ReadingAmmoniaList implements Iterable<Reading> {
    public final ObservableList<AmmoniaLevel> internalList = FXCollections.observableArrayList();

    private final ObservableList<AmmoniaLevel> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean containsSameDayReading(AmmoniaLevel toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a task to the list.
     * If a reading of the same day
     */
    public void add(AmmoniaLevel toAdd) {
        requireNonNull(toAdd);
        if (containsSameDayReading(toAdd)) {
            internalList.remove(toAdd);
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces a {@code Todo} in the list with {@code editedTodo}.
     * {@code target} must exist in the list.
     * The {@code editedTodo} must not be the same as an existing {@code Todo} in the list.
     */
    public void setAmmoniaLevel(AmmoniaLevel target, AmmoniaLevel editedAmmoniaLevel) {
        requireAllNonNull(target, editedAmmoniaLevel);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        internalList.set(index, editedAmmoniaLevel);
    }

    /**
     * Removes the equivalent Task from the list.
     * The Task must exist in the list.
     */
    public void remove(AmmoniaLevel toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(ReadingAmmoniaList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code ammoniaLevels}.
     * {@code ammoniaLevels} must not contain duplicate ammoniaLevels.
     */
    public void setTasks(List<AmmoniaLevel> ammoniaLevels) {
        requireAllNonNull(ammoniaLevels);
        if (!ammoniaLevelsAreUnique(ammoniaLevels)) {
            throw new DuplicateReadingException();
        }

        internalList.setAll(ammoniaLevels);
    }

    /**
     * Returns true if {@code Tasks} contains only unique Tasks.
     */
    private boolean ammoniaLevelsAreUnique(List<AmmoniaLevel> ammoniaLevels) {
        for (int i = 0; i < ammoniaLevels.size() - 1; i++) {
            for (int j = i + 1; j < ammoniaLevels.size(); j++) {
                if (ammoniaLevels.get(i).equals(ammoniaLevels.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
