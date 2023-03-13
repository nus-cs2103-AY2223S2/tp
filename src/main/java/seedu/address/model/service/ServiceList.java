package seedu.address.model.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.service.exception.ServiceNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of services that does not enforce uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class ServiceList implements Iterable<Service> {

    private final ObservableList<Service> internalList = FXCollections.observableArrayList();
    private final ObservableList<Service> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a vehicle to the list.
     * The vehicle must not already exist in the list.
     */
    public void add(Service toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the service {@code target} in the list with {@code editedService}.
     * {@code target} must exist in the list.
     */
    public void setService(Service target, Service editedService) {
        requireAllNonNull(target, editedService);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ServiceNotFoundException();
        }

        internalList.set(index, editedService);
    }

    /**
     * Removes the equivalent service from the list.
     * The service must exist in the list.
     */
    public void remove(Service toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ServiceNotFoundException();
        }
    }

    public void setServices(ServiceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code services}.
     */
    public void setServices(List<Service> services) {
        requireAllNonNull(services);
        internalList.setAll(services);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Service> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Service> iterator() {
        return internalList.iterator();
    }


    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
