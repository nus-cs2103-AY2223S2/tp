package seedu.patientist.model;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * Represents the in-memory model of the patientist book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Patientist patientist;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given patientist and userPrefs.
     */
    public ModelManager(ReadOnlyPatientist patientist, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(patientist, userPrefs);

        logger.fine("Initializing with patientist book: " + patientist + " and user prefs " + userPrefs);

        this.patientist = new Patientist(patientist);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.patientist.getPersonList());
    }

    public ModelManager() {
        this(new Patientist(), new UserPrefs());
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
    public Path getPatientistFilePath() {
        return userPrefs.getPatientistFilePath();
    }

    @Override
    public void setPatientistFilePath(Path patientistFilePath) {
        requireNonNull(patientistFilePath);
        userPrefs.setPatientistFilePath(patientistFilePath);
    }

    //=========== Patientist ================================================================================

    @Override
    public void setPatientist(ReadOnlyPatientist patientist) {
        this.patientist.resetData(patientist);
    }

    @Override
    public ReadOnlyPatientist getPatientist() {
        return patientist;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return patientist.hasPerson(person);
    }

    @Override
    public boolean hasPerson(Person person, Ward ward) {
        requireAllNonNull(person, ward);
        return patientist.hasPerson(person, ward);
    }

    @Override
    public void deleteStaff(Staff target) {
        patientist.removeStaff(target);
    }

    @Override
    public void deleteStaff(Staff target, Ward ward) {
        patientist.removeStaff(target, ward);
    }

    @Override
    public void deletePatient(Patient target, Ward ward) {
        patientist.removePatient(target, ward);
    }

    @Override
    public void deletePerson(Person target) {
        patientist.removePerson(target);
    }

    @Override
    public void deletePerson(Person target, Ward ward) {
        patientist.removePerson(target, ward);
    }

    @Override
    public void addPatient(Patient patient, Ward ward) {
        patientist.addPatient(patient, ward);
    }

    @Override
    public void addStaff(Staff staff, Ward ward) {
        patientist.addStaff(staff, ward);
    }


    @Override
    public void setPatient(Patient target, Patient edited) {
        requireAllNonNull(target, edited);
        patientist.setPatient(target, edited);
    }

    @Override
    public void setStaff(Staff target, Staff edited) {
        requireAllNonNull(target, edited);
        patientist.setStaff(target, edited);
    }

    @Override
    public void setPerson(Person target, Person edited) {
        requireAllNonNull(target, edited);
        if (target instanceof Staff) {
            patientist.setStaff((Staff) target, (Staff) edited);
        }
        if (target instanceof Patient) {
            patientist.setPatient((Patient) target, (Patient) edited);
        }
    }

    @Override
    public boolean hasWard(Ward ward) {
        return patientist.hasWard(ward);
    }

    @Override
    public void addWard(Ward ward) {
        patientist.addWard(ward);
    }

    @Override
    public void deleteWard(Ward ward) {
        patientist.deleteWard(ward);
    }

    @Override
    public void setWard(Ward target, Ward editedWard) {
        patientist.setWard(target, editedWard);
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
        return patientist.equals(other.patientist)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
