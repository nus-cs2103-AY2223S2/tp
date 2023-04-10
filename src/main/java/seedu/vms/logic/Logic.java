package seedu.vms.logic;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
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
     * Loads all managers for {@code Model}.
     *
     * @param beyondDeathErrHandler - the {@code BiConsumer} that will be
     *      called if an error that cannot be handled occurs. The first
     *      parameter is the title of the error while the second is the
     *      description.
     */
    void loadManagers(BiConsumer<String, String> beyondDeathErrHandler);


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
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /**
     * Returns the {@code ObjectProperty} of the patient to be detailed.
     */
    ObjectProperty<IdData<Patient>> detailedPatientProperty();


    /**
     * Returns the {@code ObjectProperty} of the vaccination to be detailed.
     */
    ObjectProperty<VaxType> detailedVaxTypeProperty();


    /**
     * Binds the given {@code ObservableList} to the {@code Model} within this
     * {@code Logic}. The list is used to refer to {@code VaxType} by index if
     * needed.
     *
     * @param displayList - the {@code ObservableList} to bind to
     *      {@code Model}.
     */
    void bindVaccinationDisplayList(ObservableList<VaxType> displayList);


    /**
     * Sets the action that should be performed if a exit command is received.
     *
     * @param closeAction - a {@code Runnable} that defines the close action.
     */
    void setCloseAction(Runnable closeAction);


    /**
     * Sets the action that should be performed if a help command is received.
     *
     * @param showHelpAction - a {@code Runnable} that defines the show help
     *      action.
     */
    void setShowHelpAction(Runnable showHelpAction);
}
