package seedu.vms.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.GuiSettings;
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
     * Queues the command text for execution.
     *
     * @param commandText - the command text to queue.
     */
    void queue(String commandText);


    /**
     * Sets the action to be performed when a command completes its execution.
     *
     * @param onExecutionComplete - the {@code Consumer} to be called after a
     *      command completes its execution.
     */
    void setOnExecutionCompletion(Consumer<List<CommandMessage>> onExecutionComplete);


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
    ObservableMap<Integer, IdData<Appointment>> getFilteredAppointmentMap();

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
