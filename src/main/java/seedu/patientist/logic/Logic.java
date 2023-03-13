package seedu.patientist.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.logic.commands.CommandResult;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.person.Person;

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
     * Returns the Patientist.
     *
     * @see seedu.patientist.model.Model#getPatientist()
     */
    ReadOnlyPatientist getPatientist();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' patientist book file path.
     */
    Path getPatientistPath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
