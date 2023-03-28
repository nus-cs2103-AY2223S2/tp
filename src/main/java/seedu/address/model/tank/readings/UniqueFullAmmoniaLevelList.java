package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tank.readings.exceptions.DuplicateReadingException;
import seedu.address.model.tank.readings.exceptions.ReadingNotFoundException;

/**
 * A list of Ammonia Readings of all tanks
 *
 * Supports a minimal set of list operations.
 */
public class UniqueFullAmmoniaLevelList implements Iterable<UniqueIndividualAmmoniaLevelList> {
    public final ObservableList<UniqueIndividualAmmoniaLevelList> internalList = FXCollections.observableArrayList();

    private final ObservableList<UniqueIndividualAmmoniaLevelList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent reading as the given argument.
     */
    public boolean containsSameDayReading(UniqueIndividualAmmoniaLevelList toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a reading to the list.
     * If a reading of the same day
     */
    public void add(UniqueIndividualAmmoniaLevelList toAdd) {
        requireNonNull(toAdd);
        if (containsSameDayReading(toAdd)) {
            internalList.remove(toAdd);
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces a {@code UniqueIndividualAmmoniaLevelList} in the list with
     * {@code editedUniqueIndividualAmmoniaLevelList}.
     * {@code target} must exist in the list.
     */
    public void setUniqueIndividualAmmoniaLevelList(
            UniqueIndividualAmmoniaLevelList target,
            UniqueIndividualAmmoniaLevelList editedUniqueIndividualAmmoniaLevelList) {
        requireAllNonNull(target, editedUniqueIndividualAmmoniaLevelList);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReadingNotFoundException();
        }

        internalList.set(index, editedUniqueIndividualAmmoniaLevelList);
    }

    /**
     * Removes the equivalent reading from the list.
     * The reading must exist in the list.
     */
    public void remove(UniqueIndividualAmmoniaLevelList toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReadingNotFoundException();
        }
    }

    public void setAmmoniaLevelLists(UniqueFullAmmoniaLevelList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code UniqueIndividualAmmoniaLevelLists}.
     * {@code UniqueIndividualAmmoniaLevelLists} must not contain duplicate UniqueIndividualAmmoniaLevelLists.
     */
    public void setAmmoniaLevelLists(List<UniqueIndividualAmmoniaLevelList> uniqueIndividualAmmoniaLevelLists) {
        requireAllNonNull(uniqueIndividualAmmoniaLevelLists);
        if (!ammoniaLevelListsAreUnique(uniqueIndividualAmmoniaLevelLists)) {
            throw new DuplicateReadingException();
        }

        internalList.setAll(uniqueIndividualAmmoniaLevelLists);
    }

    /**
     * Returns true if {@code Readings} contains only unique Readings.
     */
    private boolean ammoniaLevelListsAreUnique(List<UniqueIndividualAmmoniaLevelList> ammoniaLevelLists) {
        for (int i = 0; i < ammoniaLevelLists.size() - 1; i++) {
            for (int j = i + 1; j < ammoniaLevelLists.size(); j++) {
                if (ammoniaLevelLists.get(i).equals(ammoniaLevelLists.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<UniqueIndividualAmmoniaLevelList> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<UniqueIndividualAmmoniaLevelList> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFullAmmoniaLevelList // instanceof handles nulls
                && internalList.equals(((UniqueFullAmmoniaLevelList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public int size() {
        return internalList.size();
    }
}
