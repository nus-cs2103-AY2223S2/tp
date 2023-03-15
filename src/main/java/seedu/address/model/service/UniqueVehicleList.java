package seedu.address.model.service;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.service.exception.DuplicateVehicleException;
import seedu.address.model.service.exception.VehicleNotFoundException;

/**
 * A list of vehicles that enforces uniqueness between its elements and does not allow nulls.
 * A vehicle is considered unique by comparing using
 * {@code Vehicle#isSameVehicle(Vehicle)}. As such, adding and updating of
 * vehicles uses Vehicle#isSameVehicle(Vehicle) for equality so as to ensure
 * that the vehicle being added or updated is
 * unique in terms of plate number in the UniqueVehicleList. However, the removal of a
 * vehicle uses Vehicle#equals(Object) so
 * that we ensure vehicles with the same car plates will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Vehicle#isSameVehicle(Vehicle)
 */
public class UniqueVehicleList implements Iterable<Vehicle> {

    private final ObservableList<Vehicle> internalList = FXCollections.observableArrayList();
    private final ObservableList<Vehicle> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent vehicle as the given argument.
     */
    public boolean contains(Vehicle toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameVehicle);
    }

    /**
     * Adds a vehicle to the list.
     * The vehicle must not already exist in the list.
     */
    public void add(Vehicle toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVehicleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the vehicle {@code target} in the list with {@code editedVehicle}.
     * {@code target} must exist in the list.
     * The vehicle car plate of {@code editedVehicle} must not be the same as another existing vehicle in the list.
     */
    public void setVehicle(Vehicle target, Vehicle editedVehicle) {
        requireAllNonNull(target, editedVehicle);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VehicleNotFoundException();
        }

        if (!target.isSameVehicle(editedVehicle) && contains(editedVehicle)) {
            throw new DuplicateVehicleException();
        }

        internalList.set(index, editedVehicle);
    }

    /**
     * Removes the equivalent vehicle from the list.
     * The vehicle must exist in the list.
     */
    public void remove(Vehicle toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VehicleNotFoundException();
        }
    }

    public void setVehicles(UniqueVehicleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code vehicles}.
     * {@code vehicles} must not contain duplicate vehicles.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        requireAllNonNull(vehicles);
        if (!customersAreUnique(vehicles)) {
            throw new DuplicateVehicleException();
        }

        internalList.setAll(vehicles);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Vehicle> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Vehicle> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueVehicleList // instanceof handles nulls
                        && internalList.equals(((UniqueVehicleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code vehicles} contains only unique vehicles.
     */
    private boolean customersAreUnique(List<Vehicle> vehicles) {
        for (int i = 0; i < vehicles.size() - 1; i++) {
            for (int j = i + 1; j < vehicles.size(); j++) {
                if (vehicles.get(i).isSameVehicle(vehicles.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
