package seedu.patientist.model;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.exceptions.DuplicatePersonException;
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
        this.patientist.updatePersonList();
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
    public boolean hasPerson(Person person) { //checked
        requireNonNull(person);
        return patientist.hasPerson(person);
    }

    @Override
    public boolean hasPerson(Person person, Ward ward) { //checked
        requireAllNonNull(person, ward);
        return patientist.hasPerson(person, ward);
    }

    @Override
    public boolean hasPatient(Patient patient, Ward ward) { //checked
        return hasPerson(patient, ward);
    }

    @Override
    public boolean hasStaff(Staff staff, Ward ward) { //checked
        return hasPerson(staff, ward);
    }

    @Override
    public void deleteStaff(Staff target, Ward ward) { //checked
        patientist.removeStaff(target, ward);
    }

    @Override
    public void deletePatient(Patient target, Ward ward) { //checked
        patientist.removePatient(target, ward);
    }

    @Override
    public void addPatient(Patient patient, Ward ward) { //checked
        if (hasPerson(patient)) { // global uniqueness check
            throw new DuplicatePersonException();
        }
        patientist.addPatient(patient, ward);
    }

    @Override
    public void addStaff(Staff staff, Ward ward) { //checked
        if (hasPerson(staff)) { // global uniqueness check
            throw new DuplicatePersonException();
        }
        patientist.addStaff(staff, ward);
    }


    @Override
    public void setPatient(Patient target, Patient edited) { //checked?
        requireAllNonNull(target, edited);
        patientist.setPatient(target, edited);
    }

    @Override
    public void setStaff(Staff target, Staff edited) { //checked?
        requireAllNonNull(target, edited);
        patientist.setStaff(target, edited);
    }

    @Override
    public void transferPatient(Patient patient, Ward original, Ward target) {
        patientist.transferPatient(patient, original, target);
    }

    @Override
    public void transferStaff(Staff staff, Ward original, Ward target) {
        patientist.transferStaff(staff, original, target);
    }

    @Override
    public boolean hasWard(Ward ward) { //checked
        return patientist.hasWard(ward);
    }

    @Override
    public void addWard(Ward ward) { //checked
        patientist.addWard(ward);
    }

    @Override
    public void deleteWard(Ward ward) { //checked
        patientist.deleteWard(ward);
    }

    @Override
    public void setWard(Ward target, Ward editedWard) { //checked
        patientist.setWard(target, editedWard);
    }

    @Override
    public List<String> getWardNames() {
        return this.patientist.getWardNames();
    }

    @Override
    public Ward getWard(String wardName) {
        requireAllNonNull(wardName);
        return this.patientist.getWard(wardName);
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
