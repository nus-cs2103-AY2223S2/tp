package seedu.medinfo.model.ward;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.medinfo.model.ward.Ward.wardWithName;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.exceptions.DuplicatePatientException;
import seedu.medinfo.model.ward.exceptions.DuplicateWardException;
import seedu.medinfo.model.ward.exceptions.InsufficientCapacityException;
import seedu.medinfo.model.ward.exceptions.WardFullException;
import seedu.medinfo.model.ward.exceptions.WardNotFoundException;

/**
 * A list of wards that enforces uniqueness between its elements and does not
 * allow nulls.
 * A ward is considered unique by comparing using
 * {@code Ward#isSameWard(Ward)}. As such, adding and updating of
 * wards uses Ward#isSameWard(Ward) for equality so as to ensure that
 * the ward being added or updated is
 * unique in terms of identity in the UniqueWardList. However, the removal of
 * a ward uses Ward#equals(Object) so
 * as to ensure that the ward with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Ward#isSameWard(Ward)
 */
public class UniqueWardList implements Iterable<Ward> {

    private final ObservableList<Ward> internalList = FXCollections.observableArrayList();
    private final ObservableList<Ward> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Initializes wardlist with default Waiting Room ward with capacity of 30
     * inside.
     */
    public UniqueWardList() {
        WardName waitingRoomName = new WardName("Waiting Room");
        Ward waitingRoom = new Ward(waitingRoomName);
        add(waitingRoom);
    }

    /**
     * Returns size of list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns total capacity of all wards.
     */
    public int capacity() {
        int capacity = 0;
        for (Ward ward: internalList) {
            Capacity cap = ward.getCapacity();
            capacity += cap.getValue();
        }
        return capacity;
    }

    /**
     * Returns specified ward to edit.
     */
    public Ward getWard(String wardName) {
        return internalList.get(internalList.indexOf(wardWithName(wardName)));
    }

    /**
     * Returns true if the list contains an equivalent ward as the given
     * {@code Ward}.
     */
    public boolean contains(Ward toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameWard);
    }

    /**
     * Returns true if the list contains an equivalent ward as the given
     * {@code String}.
     */
    public boolean contains(String toCheckName) {
        requireNonNull(toCheckName);
        WardName wardName = new WardName(toCheckName);
        Ward toCheck = new Ward(wardName);
        return internalList.stream().anyMatch(toCheck::isSameWard);
    }

    /**
     * Adds a ward to the list.
     * The ward must not already exist in the list.
     */
    public void add(Ward toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
        }
    }

    /**
     * Adds patient p to their assigned ward.
     * 
     * @param p
     */
    public void addPatient(Patient p) throws CommandException, WardFullException {
        requireNonNull(p);
        String targetName = p.getWardNameString();
        int index = internalList.indexOf(wardWithName(targetName));
        Ward target = internalList.get(index);
        target.addPatient(p);
        internalList.set(index, target);
    }

    /**
     * Replaces the ward {@code target} in the list with {@code editedWard}.
     * {@code target} must exist in the list.
     * The ward identity of {@code editedWard} must not be the same as another
     * existing ward in the list.
     */
    public void setWard(Ward target, Ward editedWard) {
        requireAllNonNull(target, editedWard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new WardNotFoundException();
        }

        if (!target.isSameWard(editedWard) && contains(editedWard)) {
            throw new DuplicatePatientException();
        }

        if (editedWard.getCapacity().getValue() < target.getOccupancy()) {
            throw new InsufficientCapacityException();
        }

        ObservableList<Patient> patients = target.getPatientList();
        for (Patient patient : patients) {
            editedWard.addPatient(patient);
        }

        internalList.set(index, editedWard);
    }

    /**
     * Replaces the ward {@code target} in the target's ward with
     * {@code editedPatient}.
     * {@code target} must exist in the ward.
     */
    public void setPatient(Patient target, Patient editedPatient) throws CommandException, WardFullException{
        String targetName = target.getWardNameString();
        String editedName = editedPatient.getWardNameString();
        int targetIndex = internalList.indexOf(wardWithName(targetName));
        int editedIndex = internalList.indexOf(wardWithName(editedName));

        if (!targetName.equals(editedName)) {
            changePatientWard(target, targetIndex, editedIndex);
        } else {
            Ward targetWard = internalList.get(targetIndex);
            targetWard.setPatient(target, editedPatient);
            internalList.set(targetIndex, targetWard);
        }
    }

    /**
     * Moves patient from one ward to another
     * 
     * @param target The target patient
     * @param from   The patient's current ward index in internalList.
     * @param to     The patient's next ward index in internalList.
     */
    public void changePatientWard(Patient target, int from, int to) throws WardFullException {
        Ward start = internalList.get(from);
        Ward end = internalList.get(to);
        end.addPatient(target);
        start.removePatient(target);
        internalList.set(from, start);
        internalList.set(to, end);
    }

    /**
     * Removes the equivalent ward from the list.
     * The ward must exist in the list.
     */
    public void remove(Ward toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new WardNotFoundException();
        }
    }

    /**
     * Removes the equivalent patient from their assigned ward.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        String targetName = toRemove.getWardNameString();
        int index = internalList.indexOf(wardWithName(targetName));
        Ward targetWard = internalList.get(index);
        targetWard.removePatient(toRemove);
        internalList.set(index, targetWard);
    }

    public void setWards(UniqueWardList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code wards}.
     * {@code wards} must not contain duplicate wards.
     */
    public void setWards(List<Ward> wards) {
        requireAllNonNull(wards);
        if (!wardsAreUnique(wards)) {
            throw new DuplicateWardException();
        }

        internalList.setAll(wards);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Ward> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Ward> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueWardList // instanceof handles nulls
                        && internalList.equals(((UniqueWardList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code patients} contains only unique patients.
     */
    private boolean wardsAreUnique(List<Ward> wards) {
        for (int i = 0; i < wards.size() - 1; i++) {
            for (int j = i + 1; j < wards.size(); j++) {
                if (wards.get(i).isSameWard(wards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
