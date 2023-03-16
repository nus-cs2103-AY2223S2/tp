package seedu.socket.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.socket.commons.core.GuiSettings;
import seedu.socket.logic.commands.CommandResult;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.person.Person;

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
     * Returns the SOCket.
     *
     * @see seedu.socket.model.Model#getSocket()
     */
    ReadOnlySocket getSocket();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns a person to be viewed */
    ObservableList<Person> getViewedPerson();

    /**
     * Set the viewed person.
     */
    void setViewedPerson(int index);

    /**
     * Returns the user prefs' SOCket file path.
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
