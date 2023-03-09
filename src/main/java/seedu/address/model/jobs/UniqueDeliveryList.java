package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * UniqueDeliveryList
 */
public class UniqueDeliveryList implements Iterable<DeliveryJob> {

    private final ObservableList<DeliveryJob> internalList = FXCollections.observableArrayList();
    private final ObservableList<DeliveryJob> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<DeliveryJob> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<DeliveryJob> iterator() {
        return internalList.iterator();
    }

    /**
     * add
     *
     * @param toAdd
     */
    public void add(DeliveryJob toAdd) {
        requireNonNull(toAdd);
        // if (contains(toAdd)) {
        //     throw new DuplicatePersonException();
        // }
        internalList.add(toAdd);
    }
}
