package seedu.dengue.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.dengue.commons.core.GuiSettings;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.overview.Overview;
import seedu.dengue.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' Dengue Hotspot Tracker file path.
     */
    Path getDengueHotspotTrackerFilePath();

    /**
     * Sets the user prefs' Dengue Hotspot Tracker file path.
     */
    void setDengueHotspotTrackerFilePath(Path dengueHotspotTrackerFilePath);

    /**
     * Replaces Dengue Hotspot Tracker data with the data in {@code dengueHotspotTracker}.
     */
    void setDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker);

    /** Returns the DengueHotspotTracker */
    ReadOnlyDengueHotspotTracker getDengueHotspotTracker();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Dengue Hotspot Tracker.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the Dengue Hotspot Tracker.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the Dengue Hotspot Tracker.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the Dengue Hotspot Tracker.
     * The person identity of {@code editedPerson} must not be the same as another existing person in
     * the Dengue Hotspot Tracker.
     */
    void setPerson(Person target, Person editedPerson);

    void setPersons(List<Person> persons);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the overview of the model.
     */
    Overview getOverview();

    /**
     * Sets the given overview as the new overview.
     *
     * @param overview The new overview type to replace the current.
     */
    void setOverview(Overview overview);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFromMemoryStack();

    /**
     * Undo an action.
     * @throws CommandException if cannot undo further.
     */
    void undo() throws CommandException;

    /**
     * Redo an action.
     * @throws CommandException if cannot redo further.
     */
    void redo() throws CommandException;

    /**
     * Sorts the list.
     * @throws CommandException if sort type not specified.
     */
    void sort(List<Person> sortedList);
}
