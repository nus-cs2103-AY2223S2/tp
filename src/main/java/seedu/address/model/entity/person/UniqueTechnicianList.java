package seedu.address.model.entity.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entity.person.exceptions.DuplicatePersonException;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of technicians that enforces uniqueness between its elements and does not allow nulls.
 * A technician is considered unique by comparing using {@code Technician#isSameTechnician(Customer)}. As such, adding and updating of
 * technicians uses Technician#isSameStaff(Staff) for equality so as to ensure that the technician being added or updated is
 * unique in terms of identity in the UniqueTechnicianList. However, the removal of a technician uses Staff#equals(Object) so
 * that we ensure technicians with the same staff id will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Technician#isSameStaff(Staff)
 */
public class UniqueTechnicianList implements Iterable<Technician> {

    private final ObservableList<Technician> internalList = FXCollections.observableArrayList();
    private final ObservableList<Technician> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Technician as the given argument.
     */
    public boolean contains(Technician toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStaff);
    }

    /**
     * Adds a technician to the list.
     * The technician must not already exist in the list.
     */
    public void add(Technician toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the technician {@code target} in the list with {@code editedTechnician}.
     * {@code target} must exist in the list.
     * The identity of {@code editedTechnician} must not be the same as another existing technician in the list.
     */
    public void setTechnician(Technician target, Technician editedTechnician) {
        requireAllNonNull(target, editedTechnician);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameStaff(editedTechnician) && contains(editedTechnician)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTechnician);
    }

    /**
     * Removes the equivalent technician from the list.
     * The technician must exist in the list.
     */
    public void remove(Technician toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setTechnicians(UniqueTechnicianList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code technicians}.
     * {@code technicians} must not contain duplicate technicians.
     */
    public void setTechnicians(List<Technician> technicians) {
        requireAllNonNull(technicians);
        if (!techniciansAreUnique(technicians)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(technicians);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Technician> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Technician> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTechnicianList // instanceof handles nulls
                        && internalList.equals(((UniqueTechnicianList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code technicians} contains only unique technicians.
     */
    private boolean techniciansAreUnique(List<Technician> technicians) {
        for (int i = 0; i < technicians.size() - 1; i++) {
            for (int j = i + 1; j < technicians.size(); j++) {
                if (technicians.get(i).isSameStaff(technicians.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
