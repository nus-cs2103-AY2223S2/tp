package seedu.address.logic.trackereventsystem;

import seedu.address.model.module.ReadOnlyModule;

/**
 * Represents an observer that is triggered when a module is added, edited, or deleted.
 */
public interface OnModuleEditedEventObserver {
    /**
     * Called when a module is added, edited, or deleted.
     *
     * @param originalModule The original module. {@code null} if the event was triggered by a module being added.
     * @param editedModule The edited module. {@code null} if the event was triggered by a module being deleted.
     */
    void onModuleEdited(ReadOnlyModule originalModule, ReadOnlyModule editedModule);
}
