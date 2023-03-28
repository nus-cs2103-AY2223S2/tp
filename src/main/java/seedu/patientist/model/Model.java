package seedu.patientist.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
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
     * Returns the user prefs' patientist book file path.
     */
    Path getPatientistFilePath();

    /**
     * Sets the user prefs' patientist book file path.
     */
    void setPatientistFilePath(Path patientistFilePath);

    /**
     * Replaces patientist book data with the data in {@code patientist}.
     */
    void setPatientist(ReadOnlyPatientist patientist);

    /** Returns the Patientist */
    ReadOnlyPatientist getPatientist();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the patientist book (all wards).
     * This method makes use of {@code Person::isSamePerson} to check identity
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the given {@code ward}.
     * {@code ward} must exist.
     * Patients are uniquely identified by Patient ID, through {@code Patient::isSamePerson}
     * Staff are uniquely identified by name, through {@code Staff::isSamePerson}
     */
    boolean hasPerson(Person person, Ward ward);

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the ward.
     * Patients are uniquely identified by ID, through {@code Person::isSamePerson}.
     */
    boolean hasPatient(Patient patient, Ward ward);

    /**
     * Returns true if a staff with the same identity as {@code staff} exists in the ward.
     * Staff are uniquely identified by ID, through {@code Person::isSamePerson}.
     */
    boolean hasStaff(Staff staff, Ward ward);

    /**
     * Deletes the given staff from the given ward. Other instances of this staff in other wards are untouched.
     * The staff must exist in the ward.
     */
    void deleteStaff(Staff target, Ward ward);

    /**
     * Deletes the given patient.
     * Ward must exist, and patient must be in the ward.
     */
    void deletePatient(Patient target, Ward ward);

    /**
     * Adds the given patient to the ward.
     * {@code patient} must not already exist in the patientist book.
     * {@code ward} must exist
     */
    void addPatient(Patient patient, Ward ward);

    /**
     * Adds the given staff to the ward.
     * {@code person} must not already exist in the ward.
     * {@code ward} must exist
     */
    void addStaff(Staff staff, Ward ward);

    /**
     * Replaces target Patient with edited Patient.
     * Target patient must exist in ward, edited patient must not already exist.
     */
    void setPatient(Patient target, Patient edited);

    /**
     * Replaces target Staff with edited Staff throughout whole Patientist.
     * Target Staff must exist in ward, edited Staff must not already exist.
     */
    void setStaff(Staff target, Staff edited);

    /**
     * Transfers patient from original ward to target ward.
     * Patient must exist in original ward.
     */
    void transferPatient(Patient patient, Ward original, Ward target);

    /**
     * Transfers staff from original ward to target ward.
     * Staff must exist in original ward.
     */
    void transferStaff(Staff staff, Ward original, Ward target);

    /**
     * Returns true if a ward with the same name as {@code ward} exists in the patientist book
     */
    boolean hasWard(Ward ward);

    /**
     * Adds the given ward.
     * {@code ward} must not already exist in the patientist book.
     * Wards are uniquely identified by name, through {@code Ward::equals}
     * @param ward
     */
    void addWard(Ward ward);

    /**
     * Deletes the given ward.
     * The ward must exist in the patientist book.
     */
    void deleteWard(Ward ward);

    /**
     * Replaces the given ward {@code ward} with {@code editedWard}.
     * {@code ward} must exist in the patientist book.
     * The ward identity of {@code editedWard} must not be the same as another existing ward in the patientist.
     * @param target
     * @param editedWard
     */
    void setWard(Ward target, Ward editedWard);

    /**
     * Returns {@code List} of ward names in the model.
     */
    List<String> getWardNames();

    /**
     * Returns the Ward object with the associated name in the Patientist, if it exists.
     * Else, null is returned.
     */
    Ward getWard(String wardName);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
