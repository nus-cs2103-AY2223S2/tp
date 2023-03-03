package seedu.modtrek.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_ModuleS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getDegreeProgressionFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setDegreeProgressionFilePath(Path degreeProgressionFilePath);

    /**
     * Replaces address book data with the data in {@code DegreeProgression}.
     */
    void setDegreeProgression(ReadOnlyDegreeProgression degreeProgression);

    /** Returns the DegreeProgression */
    ReadOnlyDegreeProgression getDegreeProgression();

    /**
     * Returns true if a Module with the same identity as {@code Module} exists in the address book.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given Module.
     * The Module must exist in the address book.
     */
    void deleteModule(Module target);

    /**
     * Adds the given Module.
     * {@code Module} must not already exist in the address book.
     */
    void addModule(Module module);

    /**
     * Replaces the given Module {@code target} with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The Module identity of {@code editedModule} must not be the same as another existing Module in the address book.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered Module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered Module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
