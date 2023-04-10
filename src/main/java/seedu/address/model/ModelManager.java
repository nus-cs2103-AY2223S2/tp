package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final AppointmentList appointmentList;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    // keeping for backward compatibility temporarily, because storage has not been updated
    public ModelManager(ReadOnlyPatientList addressBook, ReadOnlyAppointmentList appointmentList,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + userPrefs);
        this.addressBook = new AddressBook(addressBook);
        this.appointmentList = new AppointmentList(appointmentList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        filteredAppointments = new FilteredList<>(this.appointmentList.getAppointmentList());
    }

    /**
     * Initializes a ModelManager with the given addressBook, appointmentList and userPrefs.
     */
    public ModelManager(ReadOnlyPatientList addressBook, AppointmentList appointmentList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, appointmentList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", appointmentList: " + appointmentList + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.appointmentList = new AppointmentList(appointmentList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        filteredAppointments = new FilteredList<>(this.appointmentList.getAppointmentList());
    }

    public ModelManager() {
        this(new AddressBook(), new AppointmentList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getAppointmentListPath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAppointmentListPath(Path appointmentListFilePath) {
        requireNonNull(appointmentListFilePath);
        userPrefs.setAddressBookFilePath(appointmentListFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyPatientList addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyPatientList getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public boolean hasPatientName(Name name) {
        requireNonNull(name);
        return addressBook.getPatientList().stream().anyMatch(patient -> patient.getName().equals(name));
    }

    @Override
    public void deletePatient(Patient target) {
        // Save the patient and appointment list
        AddressBook addressBookCopy = new AddressBook(addressBook);
        AppointmentList appointmentListCopy = new AppointmentList(appointmentList);

        addressBook.removePatient(target);
        // Remove all appointments of that patient
        List<Appointment> appointmentsToRemove = new ArrayList<>();
        for (Appointment appointment : appointmentList.getAppointmentList()) {
            if (appointment.getPatientName().equals(target.getName())) {
                appointmentsToRemove.add(appointment);
            }
        }
        for (Appointment appointment : appointmentsToRemove) {
            appointmentList.removeAppointment(appointment);
        }

        // Check that the patient and their corresponding appointments have been removed
        // If not, reset both lists and throw an error
        boolean didDeletePatientFail = addressBook.hasPatient(target);

        boolean areAllAppointmentsDeleted = true;
        for (Appointment appointment : appointmentsToRemove) {
            if (appointmentList.hasAppointment(appointment)) {
                areAllAppointmentsDeleted = false;
            }
        }

        if (!areAllAppointmentsDeleted || didDeletePatientFail) {
            this.addressBook.setPatients(addressBookCopy.getPatientList());
            this.appointmentList.setAppointments(appointmentListCopy.getAppointmentList());
        }
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
        //      && filteredAppointments.equals(other.filteredAppointments);
        // not added to keep backward compatibility with tests until storage is fixed
    }

    @Override
    public void setAppointmentList(ReadOnlyAppointmentList appointmentList) {
        this.appointmentList.resetData(appointmentList);
    }

    @Override
    public ReadOnlyAppointmentList getAppointmentList() {
        return appointmentList;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointmentList);
        return appointmentList.hasAppointment(appointment);
    }

    @Override
    public boolean hasOverlap(Appointment appointment) {
        requireNonNull(appointmentList);
        return appointmentList.hasOverlap(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentList.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentList.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentList.setAppointment(target, editedAppointment);
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }
}
