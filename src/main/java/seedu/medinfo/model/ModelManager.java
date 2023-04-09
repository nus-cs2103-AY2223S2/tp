package seedu.medinfo.model;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;

/**
 * Represents the in-memory model of the MedInfo data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MedInfo medInfo;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Ward> filteredWards;

    /**
     * Initializes a ModelManager with the given medInfo and userPrefs.
     */
    public ModelManager(ReadOnlyMedInfo addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with MedInfo: " + addressBook + " and user prefs " + userPrefs);

        this.medInfo = new MedInfo(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.medInfo.getPatientList());
        filteredWards = new FilteredList<>(this.medInfo.getWardList());
    }

    public ModelManager() {
        this(new MedInfo(), new UserPrefs());
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
    public Path getMedInfoFilePath() {
        return userPrefs.getMedInfoFilePath();
    }

    @Override
    public void setMedInfoFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setMedInfoFilePath(addressBookFilePath);
    }

    //=========== MedInfo ================================================================================

    @Override
    public void setMedInfo(ReadOnlyMedInfo addressBook) {
        this.medInfo.resetData(addressBook);
    }

    @Override
    public ReadOnlyMedInfo getMedInfo() {
        return medInfo;
    }

    //// Patient methods =====================================================================================
    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return medInfo.hasPatient(patient);
    }

    @Override
    public boolean hasPatientNric(Patient patient) {
        requireNonNull(patient);
        return medInfo.hasPatientNric(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        medInfo.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) throws CommandException {
        medInfo.addPatient(patient);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) throws CommandException {
        requireAllNonNull(target, editedPatient);
        medInfo.setPatient(target, editedPatient);
    }

    @Override
    public void sortPatients(Comparator<Patient> comparator) {
        requireNonNull(comparator);
        medInfo.sortPatients(comparator);

    }

    //// Ward methods =====================================================================================
    @Override
    public boolean hasWard(Ward ward) {
        requireNonNull(ward);
        return medInfo.hasWard(ward);
    }

    @Override
    public void deleteWard(Ward target) {
        medInfo.removeWard(target);
    }

    @Override
    public void addWard(Ward ward) {
        medInfo.addWard(ward);
    }

    @Override
    public void setWard(Ward target, Ward editedWard) {
        requireAllNonNull(target, editedWard);
        medInfo.setWard(target, editedWard);
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedMedInfo}
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

    //=========== Filtered Ward List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Ward} backed by the internal list of
     * {@code versionedMedInfo}
     */
    @Override
    public ObservableList<Ward> getFilteredWardList() {
        return filteredWards;
    }

    @Override
    public void updateFilteredWardList(Predicate<Ward> predicate) {
        requireNonNull(predicate);
        filteredWards.setPredicate(predicate);
    }

    @Override
    public List<String> getStatsInfo() {
        return medInfo.getStatsInfo();
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
        return medInfo.equals(other.medInfo)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
    }

}
