package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.medinfo.model.patient.exceptions.DuplicatePatientException;
import seedu.medinfo.model.patient.exceptions.PatientNotFoundException;

/**
 * A list of patients that enforces uniqueness between its elements and does not
 * allow nulls.
 * A patient is considered unique by comparing using
 * {@code Patient#isSamePatient(Patient)}. As such, adding and updating of
 * patients uses Patient#isSamePatient(Patient) for equality so as to ensure that
 * the patient being added or updated is
 * unique in terms of identity in the UniquePatientList. However, the removal of
 * a patient uses Patient#equals(Object) so
 * as to ensure that the patient with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Patient#isSamePatient(Patient)
 */
public class UniquePatientList implements Iterable<Patient> {

    private final ObservableList<Patient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Patient> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);


    /**
     * Returns size of list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns total number of critical patients.
     */
    public int numberOfCritical() {
        int critical = 0;
        for (Patient patient:internalList) {
            critical = patient.getStatusDesc().equals("CRITICAL") ? critical + 1 : critical;
        }
        return critical;
    }

    /**
     * Returns true if the list contains an equivalent patient as the given
     * argument.
     * @param toCheck Patient to be checked.
     */
    public boolean contains(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePatient);
    }

    /**
     * Returns true if the list contains a patient with equivalent NRIC as the given
     * argument.
     * @param toCheck Patient to be checked.
     */
    public boolean containsNric(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameNric);
    }

    /**
     * Adds a patient to the list.
     * The patient must not already exist in the list.
     * @param toAdd Patient to be added.
     */
    public void add(Patient toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePatientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the list.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the list.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        if (!target.isSamePatient(editedPatient) && contains(editedPatient)) {
            throw new DuplicatePatientException();
        }

        internalList.set(index, editedPatient);
    }

    /**
     * Removes the equivalent patient from the list.
     * The patient must exist in the list.
     * @param toRemove Patient to be removed.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PatientNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate patients.
     * @param replacement List of patients to be set.
     */
    public void setPatients(UniquePatientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     * @param patients List of patients to be set.
     */
    public void setPatients(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!patientsAreUnique(patients)) {
            throw new DuplicatePatientException();
        }
        internalList.setAll(patients);
    }


    // @@author {Echomo-Xinyu}-reused
    // Referenced the sortPatient and replaceSort methods
    /**
     * Sorts the patient list with {@code comparator}.
     * @param comparator Comparator used.
     */
    public void sortPatients(Comparator<Patient> comparator) {
        requireNonNull(comparator);
        ArrayList<Patient> sortedList = replaceSort(internalList, comparator);
        internalList.setAll(sortedList);
    }

    /**
     * Sorts the list of patients and returns the sorted list.
     * @param observableList List to be sorted.
     * @param comparator Comparator used.
     * @return The sorted list.
     */
    private static ArrayList<Patient> replaceSort(
            ObservableList<Patient> observableList, Comparator<Patient> comparator) {
        ArrayList<Patient> duplicatedList = new ArrayList<>();
        for (int i = 0; i < observableList.size(); i++) {
            duplicatedList.add(observableList.get(i));
        }
        duplicatedList.sort(comparator);
        return duplicatedList;
    }
    //@@author

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Patient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Patient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePatientList // instanceof handles nulls
                        && internalList.equals(((UniquePatientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code patients} contains only unique patients.
     */
    private boolean patientsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePatient(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
