package seedu.sprint.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.sprint.commons.core.GuiSettings;
import seedu.sprint.logic.commands.CommandResult;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.Model;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.application.Application;

/**
 * API of the Logic component.
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
     * Returns the InternshipBook.
     *
     * @see Model#getInternshipBook()
     */
    ReadOnlyInternshipBook getInternshipBook();

    /** Returns an unmodifiable view of the filtered list of applications */
    ObservableList<Application> getFilteredApplicationList();

    /** Returns an unmodifiable view of the sorted list of applications */
    ObservableList<Application> getSortedApplicationList();

    /**
     * Returns the user prefs' internship book file path.
     */
    Path getInternshipBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
