package seedu.vms.model;

import javafx.collections.ObservableMap;


/**
 * Represents an unmodifiable view of a storage model.
 *
 * @param <T> - the type of values being stored.
 */
public interface ReadOnlyStorageModel<T> {
    /**
     * Returns an unmodifiable map view of the storage.
     *
     * @return an unmodifiable map view of the storage.
     */
    public ObservableMap<Integer, IdData<T>> getMapView();
}
