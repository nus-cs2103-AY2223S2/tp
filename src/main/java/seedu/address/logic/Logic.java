package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.statstics.StatsManager;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;

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
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns a StatsManager.
     */
    StatsManager getStatsManager();

    /** Returns an unmodifiable view of the filtered list of internship applications. */
    ObservableList<InternshipApplication> getFilteredInternshipList();

    /** Returns an unmodifiable view of the sorted filtered list of internship applications */
    ObservableList<InternshipApplication> getSortedFilteredInternshipList();

    /** Returns an unmodifiable view of the filtered list of todos. */
    ObservableList<InternshipTodo> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered list of notes. */
    ObservableList<Note> getFilteredNoteList();

    /** Returns the InternshipApplication with the most imminent interview*/
    InternshipApplication getReminderApplication();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' todo list file path.
     */
    Path getTodoListFilePath();

    /**
     * Returns the user prefs' note list file path.
     */
    Path getNoteListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
