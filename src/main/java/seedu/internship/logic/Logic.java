package seedu.internship.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.Model;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.internship.Internship;

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
     * Returns the InternBuddy.
     *
     * @see seedu.internship.model.Model#getInternBuddy()
     */
    ReadOnlyInternBuddy getInternBuddy();

    /** Returns an unmodifiable view of the filtered list of internships */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Returns the user prefs' InternBuddy file path.
     */
    Path getInternBuddyFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the selected internship.
     *
     * @return the currently selected Internship
     */
    Internship getSelectedInternship();

    /**
     * Gets the model associated with the logic object.
     * @return the model in the logic object.
     */
    Model getModel();

}
