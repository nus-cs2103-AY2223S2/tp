package tfifteenfour.clipboard.logic;

import java.nio.file.Path;

import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ReadOnlyRoster;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Roster.
     *
     * @see tfifteenfour.clipboard.model.Model#getRoster()
     */
    ReadOnlyRoster getRoster();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getRosterFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    Model getModel();
}
