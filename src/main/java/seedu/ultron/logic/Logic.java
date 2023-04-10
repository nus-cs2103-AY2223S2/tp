package seedu.ultron.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.ultron.commons.core.GuiSettings;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.CommandResult;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.opening.Opening;

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
     * Returns Ultron.
     *
     * @see seedu.ultron.model.Model#getUltron()
     */
    ReadOnlyUltron getUltron();

    /** Returns an unmodifiable view of the filtered list of openings */
    ObservableList<Opening> getFilteredOpeningList();

    /** Returns the currently selected Opening */
    Opening getSelectedOpening();

    /** Sets the currently selected Opening */
    void setSelectedOpening(Index index);

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

    /**
     * Reset the index.
     */
    void resetIndex();
}
