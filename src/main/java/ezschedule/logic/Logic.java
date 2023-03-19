package ezschedule.logic;

import java.nio.file.Path;

import ezschedule.commons.core.GuiSettings;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.Model;
import javafx.collections.ObservableList;
import ezschedule.logic.commands.CommandResult;
import ezschedule.model.ReadOnlyAddressBook;
import ezschedule.model.event.Event;

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
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of events
     *
     * @return
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
