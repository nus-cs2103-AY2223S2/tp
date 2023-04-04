package seedu.event.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.event.commons.core.GuiSettings;
import seedu.event.logic.commands.CommandResult;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.contact.Contact;
import seedu.event.model.event.Event;

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
     * Returns the EventBook.
     *
     * @see seedu.event.model.Model#getEventBook()
     */
    ReadOnlyEventBook getEventBook();

    ReadOnlyContactList getContactList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
