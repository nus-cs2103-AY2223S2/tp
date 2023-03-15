package seedu.task.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.task.commons.core.GuiSettings;
import seedu.task.logic.commands.CommandResult;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.task.Task;

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
     * Returns the TaskBook.
     *
     * @see seedu.task.model.Model#getTaskBook()
     */
    ReadOnlyTaskBook getTaskBook();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the upcoming alert list of tasks */
    ObservableList<Task> getAlertTaskList();

    /**
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
