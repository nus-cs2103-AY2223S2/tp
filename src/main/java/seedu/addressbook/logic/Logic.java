package seedu.addressbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.addressbook.commons.core.GuiSettings;
import seedu.addressbook.logic.commands.CommandResult;
import seedu.addressbook.logic.commands.exceptions.CommandException;
import seedu.addressbook.logic.parser.exceptions.ParseException;
import seedu.addressbook.model.ReadOnlyFitBook;
import seedu.addressbook.model.client.Client;

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
     * @see seedu.addressbook.model.FitBookModel#getFitBook()
     */
    ReadOnlyFitBook getFitBook();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns the user prefs' address book file path.
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
}
