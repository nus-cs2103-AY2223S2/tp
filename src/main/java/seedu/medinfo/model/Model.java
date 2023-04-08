package seedu.medinfo.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.medinfo.commons.core.GuiSettings;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Ward> PREDICATE_SHOW_ALL_WARDS = unused -> true;

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
     * Returns the user prefs' medinfo book file path.
     */
    Path getMedInfoFilePath();

    /**
     * Sets the user prefs' medinfo book file path.
     */
    void setMedInfoFilePath(Path addressBookFilePath);

    /**
     * Replaces medinfo book data with the data in {@code addressBook}.
     */
    void setMedInfo(ReadOnlyMedInfo addressBook);

    /** Returns the MedInfo */
    ReadOnlyMedInfo getMedInfo();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the medinfo book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the medinfo book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the medinfo book.
     */
    void addPatient(Patient patient) throws CommandException;

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the medinfo book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the medinfo book.
     */
    void setPatient(Patient target, Patient editedPatient) throws CommandException;

    /**
     * Sorts the list of patients by given {@code comparator}
     */
    void sortPatients(Comparator<Patient> comparator);

    /**
     * Returns true if a ward with the same identity as {@code ward} exists in the medinfo book.
     */
    boolean hasWard(Ward ward);

    /**
     * Deletes the given ward.
     * The ward must exist in the medinfo book.
     */
    void deleteWard(Ward target);

    /**
     * Adds the given ward.
     * {@code ward} must not already exist in the medinfo book.
     */
    void addWard(Ward ward);

    /**
     * Replaces the given ward {@code target} with {@code editedWard}.
     * {@code target} must exist in the medinfo book.
     * The ward identity of {@code editedWard} must not be the same as another
     * existing ward in the medinfo book.
     */
    void setWard(Ward target, Ward editedWard);

    List<String> getStatsInfo();

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered ward list */
    ObservableList<Ward> getFilteredWardList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Updates the filter of the filtered ward list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredWardList(Predicate<Ward> predicate);

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in the medinfo book.
     */
    boolean hasPatientNric(Patient patient);

}
