package seedu.vms.model;

import java.util.Collection;
import java.util.function.Predicate;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.GuiSettings;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.logic.parser.ParseResult;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.keyword.KeywordManager;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeAction;
import seedu.vms.model.vaccination.VaxTypeManager;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Keyword> PREDICATE_SHOW_ALL_KEYWORDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    /*
     * ========================================================================
     * Keywords
     * ========================================================================
     */

    void setKeywordManager(KeywordManager keywordManager);

    /** Returns the {@code KeywordManager} the model is using. */
    KeywordManager getKeywordManager();


    /**
     * Parses the specified user command.
     *
     * @param userCommand - the user command to parse.
     * @return the {@code ParseResult} that results from the parsed user
     *      command.
     * @throws ParseException if the user command cannot be parsed.
     */
    ParseResult parseCommand(String userCommand) throws ParseException;

    /**
     * Adds the given keyword.
     * {@code keyword} must not already exist in the keyword manager.
     */
    void addKeyword(Keyword keyword);

    /**
     * Deletes the given keyword.
     * The keyword must exist in the keyword manager.
     */
    void deleteKeyword(int id);


    /** Returns an unmodifiable view of the filtered keyword list */
    ObservableMap<Integer, IdData<Keyword>> getFilteredKeywordList();

    /**
     * Updates the filter of the filtered keyword list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredKeywordList(Predicate<Keyword> predicate);


    /*
     * ========================================================================
     * Patients
     * ========================================================================
     */

    /**
     * Replaces patient manager data with the data in {@code patientManager}.
     */
    void setPatientManager(ReadOnlyPatientManager patientManager);

    /** Returns the PatientManager */
    ReadOnlyPatientManager getPatientManager();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the patient manager.
     */
    boolean hasPatient(int id);

    /**
     * Deletes the given patient.
     * The patient must exist in the patient manager.
     */
    void deletePatient(int id);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the patient manager.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the patient manager.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the patient manager.
     */
    void setPatient(int id, Patient editedPatient);

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableMap<Integer, IdData<Patient>> getFilteredPatientList();


    /*
     * ========================================================================
     * Vaccination
     * ========================================================================
     */


    void setVaxTypeManager(VaxTypeManager manager);


    /** Returns the {@code VaxTypeManager} the model is using. */
    VaxTypeManager getVaxTypeManager();

    /** Performs the specified action of the {@code VaxTypeManager} that the model is using. */
    VaxType performVaxTypeAction(VaxTypeAction action) throws IllegalValueException;

    VaxType deleteVaxType(GroupName vaxName) throws IllegalValueException;


    void setVaccinationFilters(Collection<Predicate<VaxType>> filters);

    /** Returns an unmodifiable view of the filtered vaccination type map. */
    ObservableMap<String, VaxType> getFilteredVaxTypeMap();


    /*
     * ========================================================================
     * Appointment
     * ========================================================================
     */

    void setAppointmentManager(AppointmentManager manager);

    /** Returns the {@code AppointmentManager} the model is using. */
    AppointmentManager getAppointmentManager();

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the appointment manager.
     */
    void addAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the appointment manager.
     */
    void deleteAppointment(int id);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment manager.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment in the appointment manager.
     */
    void setAppointment(int id, Appointment editedAppointment);

    /**
     * Marks the given appointment as completed.
     * The appointment must exist in the appointment manager.
     */
    void markAppointment(int id);

    /**
     * Unmarks the given appointment as completed.
     * The appointment must exist in the appointment manager.
     */
    void unmarkAppointment(int id);

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /** Returns an unmodifiable view of the filtered appointment map. */
    ObservableMap<Integer, IdData<Appointment>> getFilteredAppointmentMap();
}
