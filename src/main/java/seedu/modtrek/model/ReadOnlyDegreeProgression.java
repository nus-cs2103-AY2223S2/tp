package seedu.modtrek.model;

import javafx.collections.ObservableList;
import seedu.modtrek.model.module.Module;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyDegreeProgression {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Module> getModuleList();

}
