package seedu.vms.logic;

import java.nio.file.Path;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.GuiSettings;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.vaccination.VaxType;

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
     * Returns the PatientManager.
     *
     * @see seedu.vms.model.Model#getPatientManager()
     */
    ReadOnlyPatientManager getPatientManager();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableMap<Integer, IdData<Patient>> getFilteredPatientMap();

    /** Returns an unmodifiable view of the filtered map of vaccination types. */
    ObservableMap<String, VaxType> getFilteredVaxTypeMap();

    /** Returns an unmodifiable view of the filtered map of Appointments. */
    ObservableMap<String, Appointment> getFilteredAppointmentMap();

    /**
     * Returns the user prefs' patient manager file path.
     */
    Path getPatientManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
