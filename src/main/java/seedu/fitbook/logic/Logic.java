package seedu.fitbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;

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
     * Returns the FitBook.
     *
     * @see seedu.fitbook.model.FitBookModel#getFitBook()
     */
    ReadOnlyFitBook getFitBook();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns the user prefs' FitBook file path.
     */
    Path getFitBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ObservableList<Routine> getFilteredRoutineList();
}
