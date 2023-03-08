package seedu.vms.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.GuiSettings;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.ReadOnlyAddressBook;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeAction;
import seedu.vms.model.vaccination.VaxTypeManager;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

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

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(int id);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(int id);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the address book.
     */
    void setPatient(int id, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableMap<Integer, IdData<Patient>> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered vaccination type map. */
    ObservableMap<String, VaxType> getFilteredVaxTypeMap();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the appointment manager.
     */
    void addAppointment(Appointment appointment);
    
    /** Returns the {@code VaxTypeManager} the model is using. */
    VaxTypeManager getVaxTypeManager();

    /** Performs the specified action of the {@code VaxTypeManager} that the model is using. */
    VaxType performVaxTypeAction(VaxTypeAction action) throws IllegalValueException;
}
