package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private final FilteredList<Appointment> filteredAppointments;
    private Person displayedPerson;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());

        displayedPerson = filteredPersons.size() > 0
                ? filteredPersons.get(0)
                : null;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPatientByNric(Nric nric) {
        requireNonNull(nric);
        return addressBook.hasPatientByNric(nric);
    }

    @Override
    public boolean hasDrByNric(Nric nric) {
        requireNonNull(nric);
        return addressBook.hasDrByNric(nric);
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return addressBook.hasDoctor(doctor);
    }

    @Override
    public void deletePerson(Person target) {

        addressBook.removePerson(target);
        if (target.isSamePerson(displayedPerson)) {
            displayedPerson = null;
        }
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        addressBook.addDoctor(doctor);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        // reused https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/112/files#diff-7bd09b3a54ed83a93060f9d5e74302f5af
        // fc224dd83cb8ae73eafa10a999930d
        if (target.isSamePerson(displayedPerson)) {
            displayedPerson = editedPerson;
        }
    }

    @Override
    public void setDoctor(Doctor doctor, Doctor editedDoctor) {
        requireAllNonNull(doctor, editedDoctor);

        addressBook.setDoctor(doctor, editedDoctor);
        if (doctor.isSameDoctor(editedDoctor)) {
            displayedPerson = editedDoctor;
        }
    }

    @Override
    public void setPatient(Patient patient, Patient editedPatient) {
        requireAllNonNull(patient, editedPatient);

        addressBook.setPatient(patient, editedPatient);
        if (patient.isSamePatient(editedPatient)) {
            displayedPerson = editedPatient;
        }
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void updatePersonView(Person updatedPerson) {
        displayedPerson = updatedPerson;
    }

    @Override
    public void updateFilteredPersonListNric(Nric nric) {
        requireNonNull(nric);
        updateFilteredPersonList(p -> p.getNric().equals(nric));
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
                && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
    }

    @Override
    public void bookAppointment(Appointment appointment) {
        addressBook.bookAppointment(appointment);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Person getPersonDisplay() {
        return displayedPerson;
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        addressBook.deleteAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public Person retrievePersonByNric(Nric nric) {
        return addressBook.retrievePersonByNric(nric);
    }

    /**
     * Returns the person with the given {@code nric}, returns it. This person must exist.
     * @param nric of the person
     * @return Person with a given Nric
     */
    @Override
    public Person getPersonByNric(Nric nric) {
        return addressBook.getPersonByNric(nric);
    }
}
