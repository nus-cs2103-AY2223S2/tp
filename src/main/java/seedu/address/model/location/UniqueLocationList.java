package seedu.address.model.location;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.location.exceptions.LocationNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of locations that enforces uniqueness between locations.
 */
public class UniqueLocationList implements Iterable<Location> {
    private final ObservableList<Location> internalList = FXCollections.observableArrayList();
    private final ObservableList<Location> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent location as the given argument.
     * @param toCheck the person to check
     * @return true if the list contains the location
     */
    public boolean contains(Location toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLocation);
    }

    /**
     * Add a location to the list
     * @param toAdd the location to add
     */
    public void add(Location toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent location from the list.
     * The location must exist in the list.
     * @param toRemove the location to remove.
     */
    public void remove(Location toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LocationNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Location> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Location> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueLocationList
                    && internalList.equals(((UniqueLocationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code locations} contains only unique persons.
     */
    private boolean personsAreUnique(List<Location> locations) {
        for (int i = 0; i < locations.size() - 1; i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (locations.get(i).isSameLocation(locations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
