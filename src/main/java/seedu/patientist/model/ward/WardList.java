package seedu.patientist.model.ward;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;

import java.util.Iterator;
import java.util.List;

import static seedu.patientist.commons.util.AppUtil.checkArgument;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

public class WardList implements Iterable<Ward> {

    public static final String INVALID_INDEX_MESSAGE = "Specified index needs to be in range of list indices";

    private final ObservableList<Ward> internalList = FXCollections.observableArrayList();
    private final ObservableList<Ward> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setWards(WardList wards) {
        requireAllNonNull(wards);
        internalList.setAll(wards.internalList);
    }

    public void setWards(List<Ward> wards) {
        requireAllNonNull(wards);
        if (!wardsAreUnique(wards)) {
            return; //TODO: impelemnt DuplicateWardException
        }

        internalList.setAll(wards);
    }

    public void setWard(Ward target, Ward editedWard) {
        requireAllNonNull(target, editedWard);

        int idx = internalList.indexOf(target);
        if (idx == -1) {
            return; //TODO: implement and throw WardNotFoundException
        }

        if (!target.equals(editedWard) && contains(editedWard)) {
            return; //TODO: implement and throw DuplicateWardException
        }

        internalList.set(idx, editedWard);
    }

    public void add(Ward ward) {
        requireAllNonNull(ward);
        if (contains(ward)) {
            return;//TODO: implement and throw DuplicateWardException
        }
        internalList.add(ward);
    }

    public boolean contains(Ward targetWard) {
        return internalList.stream().anyMatch(targetWard::equals);
    }

    public boolean contains(Person person) {
        for (Ward ward : internalList) {
            if (ward.containsPerson(person)) {
                return true;
            }
        }
        return false;
    }

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
