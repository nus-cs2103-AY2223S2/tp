package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResultNew;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.opening.Opening;

/**
 * API of the Logic component
 */
public interface LogicNew {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResultNew execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns Ultron.
     *
     * @see seedu.address.model.Model#getUltron()
     */
    ReadOnlyUltron getUltron();

    /** Returns an unmodifiable view of the filtered list of openings */
    ObservableList<Opening> getFilteredOpeningList();

    /**
     * Returns the user prefs' ultron file path.
     */
    Path getUltronFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
