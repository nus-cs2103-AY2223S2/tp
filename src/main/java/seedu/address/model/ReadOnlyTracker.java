package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;

/**
 * Unmodifiable view of a tracker
 */
public interface ReadOnlyTracker {

    /**
     * Returns an unmodifiable view of the module list.<p>
     * This list will not contain any duplicate modules.
     *
     * @return An unmodifiable view of the module list.
     */
    ObservableList<? extends ReadOnlyModule> getModuleList();

    /**
     * Returns the module whose module code is the same as {@code code}. If no such module exist, return null.
     *
     * @param code The code of the module to be returned.
     * @return The module whose module code is the same as {@code code}. If no such module exist, return null.
     */
    ReadOnlyModule getModule(ModuleCode code);
}
