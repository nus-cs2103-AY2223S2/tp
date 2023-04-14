package tfifteenfour.clipboard.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    Path getRosterFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setRosterFilePath(Path rosterFilePath);

    /**
     * Replaces address book data with the data in {@code roster}.
     */
    void setRoster(ReadOnlyRoster roster);

    /** Returns the Roster */
    Roster getRoster();

    /**
     * Makes a copy of the model
     */
    Model copy();

    public void setCommandTextExecuted(String commandText);

    public String getCommandTextExecuted();

    public void setCommandExecuted(Command command);

    public Command getCommandExecuted();

    public CurrentSelection getCurrentSelection();
}
