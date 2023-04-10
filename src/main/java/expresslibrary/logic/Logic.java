package expresslibrary.logic;

import java.nio.file.Path;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.logic.commands.CommandResult;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ExpressLibrary.
     *
     * @see expresslibrary.model.Model#getExpressLibrary()
     */
    ReadOnlyExpressLibrary getExpressLibrary();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of books */
    ObservableList<Book> getFilteredBookList();

    /**
     * Returns the user prefs' ExpressLibrary file path.
     */
    Path getExpressLibraryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
