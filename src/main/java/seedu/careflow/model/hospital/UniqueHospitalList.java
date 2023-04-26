package seedu.careflow.model.hospital;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.hospital.exceptions.DuplicateHospitalException;
import seedu.careflow.model.hospital.exceptions.HospitalNotFoundException;


/**
 * A list of hospitals that enforces uniqueness between its elements and does not allow nulls.
 * A hospital is considered unique by comparing using {@code Hospital#isSameHospital(Hospital)}. As such, adding and
 * updating of hospital uses Hospital#isSameHospital(Hospital) for equality so as to ensure that the hospital being
 * added or updated is unique in terms of identity in the UniqueHospitalList. However, the removal of a hospital uses
 * Hospital#equals(Object) so as to ensure that the hospital with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Hospital#isSameHospital(Hospital)
 */
public class UniqueHospitalList implements Iterable<Hospital> {
    private final ObservableList<Hospital> internalList = FXCollections.observableArrayList();
    private final ObservableList<Hospital> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent hospital as the given argument.
     */
    public boolean contains(Hospital toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameHospital);
    }

    /**
     * Adds a hospital to the list.
     * The hospital must not already exist in the list.
     */
    public void add(Hospital toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateHospitalException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the hospital {@code target} in the list with {@code editedHospital}.
     * {@code target} must exist in the list.
     * The hospital identity of {@code editedHospital} must not be the same as another existing hospital in the list.
     */
    public void setHospital(Hospital target, Hospital editedHospital) {
        requireAllNonNull(target, editedHospital);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new HospitalNotFoundException();
        }

        if (!target.isSameHospital(editedHospital) && contains(editedHospital)) {
            throw new DuplicateHospitalException();
        }

        internalList.set(index, editedHospital);
    }

    /**
     * Removes the equivalent hospital from the list.
     * The hospital must exist in the list.
     */
    public void remove(Hospital toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new HospitalNotFoundException();
        }
    }

    public void setHospitals(UniqueHospitalList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code hospitals}.
     * {@code hospitals} must not contain duplicate hospitals.
     */
    public void setHospitals(List<Hospital> hospitals) {
        requireAllNonNull(hospitals);
        if (!hospitalsAreUnique(hospitals)) {
            throw new DuplicateHospitalException();
        }

        internalList.setAll(hospitals);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Hospital> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Hospital> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueHospitalList // instanceof handles nulls
                && internalList.equals(((UniqueHospitalList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code hospitals} contains only unique hospitals.
     */
    private boolean hospitalsAreUnique(List<Hospital> hospitals) {
        for (int i = 0; i < hospitals.size() - 1; i++) {
            for (int j = i + 1; j < hospitals.size(); j++) {
                if (hospitals.get(i).isSameHospital(hospitals.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
