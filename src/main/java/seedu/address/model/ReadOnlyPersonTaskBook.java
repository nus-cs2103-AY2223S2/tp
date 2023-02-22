package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.mapping.PersonTask;

/**
 * Unmodifiable view of a person task mapping
 */
public interface ReadOnlyPersonTaskBook {
    /**
     * Returns an unmodifiable view of the person task mapping.
     * This list will not contain any duplicate person task mapping.
     */
    ObservableList<PersonTask> getPersonTaskList();
}
