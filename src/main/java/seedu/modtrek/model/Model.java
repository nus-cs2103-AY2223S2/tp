package seedu.modtrek.model;

import java.nio.file.Path;
import java.util.TreeMap;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to false
     */
    Predicate<Module> PREDICATE_SHOW_NO_MODULES = unused -> false;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs the user prefs
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     *
     * @return the user prefs
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return the gui settings
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings the gui settings
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     *
     * @return the degree progression file path
     */
    Path getDegreeProgressionFilePath();

    /**
     * Sets the user prefs' address book file path.
     *
     * @param degreeProgressionFilePath the degree progression file path
     */
    void setDegreeProgressionFilePath(Path degreeProgressionFilePath);

    /**
     * Replaces address book data with the data in {@code DegreeProgression}.
     *
     * @param degreeProgression the degree progression
     */
    void setDegreeProgression(ReadOnlyDegreeProgression degreeProgression);

    /**
     * Returns the DegreeProgression
     *
     * @return the degree progression
     */
    ReadOnlyDegreeProgression getDegreeProgression();

    /**
     * Returns true if a Module with the same identity as {@code Module} exists in the address book.
     *
     * @param module the module
     * @return the boolean
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given Module.
     * The Module must exist in the address book.
     *
     * @param target the target
     */
    void deleteModule(Module target);

    /**
     * Adds the given Module.
     * {@code Module} must not already exist in the address book.
     *
     * @param module the module
     */
    void addModule(Module module);

    /**
     * Replaces the given Module {@code target} with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The Module identity of {@code editedModule} must not be the same as another existing Module in the address book.
     *
     * @param target       the target
     * @param editedModule the edited module
     */
    void setModule(Module target, Module editedModule);

    /**
     * Returns an unmodifiable view of the filtered Module list
     *
     * @return the filtered module list
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the predicate
     */
    Predicate<Module> getPredicate();

    TreeMap<?, ObservableList<Module>> getModuleGroups();

    void sortModuleGroups(SortCommand.Sort sort);

    String getSort();

    /**
     * Updates the filter of the filtered Module list to filter by the given {@code predicate}.
     *
     * @param predicate the predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
