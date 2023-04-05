package seedu.medinfo.logic;

import java.nio.file.Path;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.logic.commands.CommandResult;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.logic.parser.exceptions.ParseException;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;

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
     * Returns the MedInfo.
     *
     * @see seedu.medinfo.model.Model#getMedInfo()
     */
    ReadOnlyMedInfo getMedInfo();

    List<String> getStatsInfo();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of wards */
    ObservableList<Ward> getFilteredWardList();

    /**
     * Returns the user prefs' medinfo book file path.
     */
    Path getMedInfoFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
