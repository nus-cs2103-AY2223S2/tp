package seedu.careflow.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.patient.exceptions.DuplicatePatientException;
import seedu.careflow.model.patient.exceptions.DuplicatePatientIcException;
import seedu.careflow.model.patient.exceptions.PatientNotFoundException;

/**
 * A list of patients that enforces uniqueness between its elements and does not allow nulls.
 * A patient is considered unique by comparing using {@code Patient#isSamePerson(Patient)}. As such, adding and
 * updating of patient uses Patient#isSamePerson(Patient) for equality so as to ensure that the patient being added or
 * updated is unique in terms of identity in the UniquePatientList. However, the removal of a patient uses
 * Patient#equals(Object) so as to ensure that the patient with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Patient#isSamePatient(Patient)
 */
public class UniquePatientList implements Iterable<Patient> {

    private final ObservableList<Patient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Patient> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent patient's name as the given argument.
     */
    public boolean containName(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePatient);
    }

    /**
     * Returns true if the list contains an equivalent patient's NRIC as the given argument.
     */
    public boolean containIc(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameIc);
    }

    /**
     * Check every patient in the list(except "except") contains equivalent patient's NRIC as the given argument, i.e.
     * toCheck.
     */
    private boolean containIc(Patient except, Patient toCheck) {
        requireAllNonNull(except, toCheck);
        return internalList.stream().filter(p -> !except.isSamePatient(p)).anyMatch(toCheck::isSameIc);
    }

    /**
     * Adds a patient to the list.
     * The patient must not already exist in the list.
     */
    public void add(Patient toAdd) {
        requireNonNull(toAdd);
        if (containName(toAdd)) {
            throw new DuplicatePatientException();
        }
        if (containIc(toAdd)) {
            throw new DuplicatePatientIcException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the list.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in term of name
     * and IC in the list.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        if (!target.isSamePatient(editedPatient) && containName(editedPatient)) {
            throw new DuplicatePatientException();
        }
        if (containIc(target, editedPatient)) {
            throw new DuplicatePatientIcException();
        }

        internalList.set(index, editedPatient);
    }

    /**
     * Removes the equivalent patient from the list.
     * The patient must exist in the list.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PatientNotFoundException();
        }
    }

    public void setPatients(UniquePatientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code patients}.
     * {@code patients} must not contain duplicate patients and ic.
     */
    public void setPatients(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!patientsAreUnique(patients)) {
            throw new DuplicatePatientException();
        }
        if (!patientsIcAreUnique(patients)) {
            throw new DuplicatePatientIcException();
        }
        internalList.setAll(patients);
    }

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

    /**
     * Returns true if {@code patients} contains only unique patients' IC.
     */
    private boolean patientsIcAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSameIc(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
