package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a tracker
 */
public interface ReadOnlyTracker {

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<seedu.address.model.module.Module> getModuleList();
}
