package seedu.address.model.ward;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.model.ward.exceptions.DuplicateWardException;
import seedu.address.model.ward.exceptions.WardNotFoundException;

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
     * Initializes wardlist with default Waiting Room ward with capacity of 30 inside.
     */
    public UniqueWardList() {
        Ward waitingRoom = new Ward("Waiting Room");
        add(waitingRoom);
    }

    /**
     * Returns size of list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if the list contains an equivalent ward as the given
     * argument.
     */
    public boolean contains(Ward toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameWard);
    }

    /**
     * Adds a ward to the list.
     * The ward must not already exist in the list.
     */
    public void add(Ward toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateWardException();
        }
        internalList.add(toAdd);
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

        internalList.set(index, editedWard);
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
