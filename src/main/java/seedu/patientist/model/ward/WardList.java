package seedu.patientist.model.ward;

import static seedu.patientist.commons.util.AppUtil.checkArgument;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;

/**
 * Representation of a list of wards that enforces uniqueness between the wards.
 * Wards are identified by their name, thus addition and deletion occur
 * based on equality check of {@code Ward::equals}, which compares name only.
 */
public class WardList implements Iterable<Ward> {

    public static final String INVALID_INDEX_MESSAGE = "Specified index needs to be in range of list indices";

    private final ObservableList<Ward> internalList = FXCollections.observableArrayList();
    private final ObservableList<Ward> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Sets internal wardlist as {@code wards}.
     */
    public void setWards(WardList wards) {
        requireAllNonNull(wards);
        internalList.setAll(wards.internalList);
    }

    /**
     * Sets internal wardlist using the provided {@code List<Ward>}
     */
    public void setWards(List<Ward> wards) {
        requireAllNonNull(wards);
        if (!wardsAreUnique(wards)) {
            return; //TODO: impelemnt DuplicateWardException
        }

        internalList.setAll(wards);
    }

    /**
     * Replaces target ward with edited ward.
     * Target ward must already exist.
     * Edited ward cannot already exist.
     */
    public void setWard(Ward target, Ward edited) {
        requireAllNonNull(target, edited);

        int idx = internalList.indexOf(target);
        if (idx == -1) {
            return; //TODO: implement and throw WardNotFoundException
        }

        if (!target.equals(edited) && contains(edited)) {
            return; //TODO: implement and throw DuplicateWardException
        }

        internalList.set(idx, edited);
    }

    /**
     * Adds given ward to wardlist.
     * Ward cannot already exist.
     */
    public void add(Ward ward) {
        requireAllNonNull(ward);
        if (contains(ward)) {
            return; //TODO: implement and throw DuplicateWardException
        }
        internalList.add(ward);
    }

    /**
     * Returns true if targetWard is in the list.
     */
    public boolean contains(Ward targetWard) {
        return internalList.stream().anyMatch(targetWard::equals);
    }

    /**
     * Returns true if person is in any of the wards in the list.
     * Person can be staff or patient.
     */
    public boolean contains(Person person) {
        for (Ward ward : internalList) {
            if (ward.containsPerson(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes ward from the wardlist
     * ward must already exist.
     */
    public void delete(Ward ward) {
        requireAllNonNull(ward);
        if (!internalList.remove(ward)) {
            return; //TODO: implement and throw WardNotFoundException
        }
    }

    public Ward get(int index) {
        checkArgument(isValidIndex(index), INVALID_INDEX_MESSAGE);
        return internalList.get(index);
    }

    public ObservableList<Ward> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < internalList.size();
    }

    @Override
    public Iterator<Ward> iterator() {
        return internalList.iterator();
    }

    private boolean wardsAreUnique(List<Ward> wards) {
        for (int i = 0; i < wards.size() - 1; i++) {
            for (int j = i + 1; j < wards.size(); j++) {
                if (wards.get(i).equals(wards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WardList // instanceof handles nulls
                && internalList.equals(((WardList) other).internalList));
    }
}
