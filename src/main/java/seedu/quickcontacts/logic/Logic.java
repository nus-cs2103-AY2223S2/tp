package seedu.quickcontacts.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.CommandResult;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Person;

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
     * Suggests a {@code Prefix} to be inputted for the current command
     * input.
     * @param commandText The command as entered by the user.
     * @return The {@code AutocompleteResult} based on the command input.
     */
    AutocompleteResult autocomplete(String commandText);

    /**
     * Returns the QuickBook.
     *
     * @see seedu.quickcontacts.model.Model#getQuickBook()
     */
    ReadOnlyQuickBook getQuickBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * @return an unmodifiable view of the filtered list of meetings
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Returns the user prefs' quick book file path.
     */
    Path getQuickBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
