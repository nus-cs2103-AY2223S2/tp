package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;
    Comparator<Module> COMPARATOR_NO_SORTING_OF_MODULES = (module1, module2) -> 0;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' module tracker file path.
     */
    Path getModuleTrackerFilePath();

    /**
     * Sets the user prefs' module tracker file path.
     */
    void setModuleTrackerFilePath(Path moduleTrackerFilePath);

    /**
     * Replaces module tracker data with the data in {@code moduleTracker}.
     */
    void setModuleTracker(ReadOnlyModuleTracker moduleTracker);

    /** Returns the ModuleTracker */
    ReadOnlyModuleTracker getModuleTracker();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the module tracker.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the module tracker.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the module tracker.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module tracker.
     * The module identity of {@code editedModule} must not be the same as another existing module in the
     * module tracker.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getDisplayedModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates the comparator of the sorted module list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedModuleList(Comparator<Module> comparator);
}
