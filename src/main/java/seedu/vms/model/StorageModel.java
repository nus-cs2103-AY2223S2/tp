package seedu.vms.model;

import java.util.Collection;
import java.util.Objects;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.ValueChange;


/**
 * Represents a storage model that manages and stores values that require an ID
 * identifier.
 *
 * @param <T> - the type of values being stored.
 */
public abstract class StorageModel<T> implements ReadOnlyStorageModel<T> {
    private final IdDataMap<T> dataMap = new IdDataMap<>();


    /** Constructs an empty {@code StorageModel}. */
    public StorageModel() {}


    /**
     * Constructs a {@code StorageModel} with the same data mapping as the
     * reference given.
     *
     * @param reference - the storage model to be copied.
     */
    public StorageModel(ReadOnlyStorageModel<T> reference) {
        this();
        resetData(reference);
    }


    // ===== Data override methods


    /**
     * Clears and sets the value contents of this storage model to the given
     * collection of values.
     *
     * <p>IDs are resetted and the values are ID-ed according the their order
     * in the collection given.
     *
     * @param values - the value contents to replace to.
     */
    public void setValues(Collection<T> values) {
        dataMap.setValues(values);
    }


    /**
     * Clears and resets the data contents of this storage model to match that
     * of the given storage model.
     *
     * <p>The IDs of the data in the given reference are preserved.
     *
     * @param reference - the storage model to copy from.
     */
    public void resetData(ReadOnlyStorageModel<T> reference) {
        dataMap.setDatas(reference.getMapView().values());
    }


    // ===== Map methods


    /**
     * Returns if the specified ID has a mapped value.
     *
     * @param id - the id to check.
     * @return {@code true} if there is a mapped value to the specified ID and
     *      {@code false} otherwise.
     */
    public boolean contains(int id) {
        return dataMap.contains(id);
    }


    /**
     * Adds the specified value to the storage.
     *
     * @param value - the value to add.
     */
    public IdData<T> add(T value) {
        return dataMap.add(value);
    }


    /**
     * Adds the specified data to the storage. If there is already a value
     * mapped to the ID of the specified data, that value is replaced with the
     * given.
     *
     * @param data - the data to add.
     */
    public IdData<T> add(IdData<T> data) {
        return dataMap.add(data);
    }


    /**
     * Sets the specified ID's value to the given value. If there is already a
     * mapping to the given ID, that ID associated value is replaced with the
     * given.
     *
     * @param id - the id to set.
     * @param value - the value to set to.
     * @throws NullPointerException if {@code value} is {@code null}.
     * @throws java.util.NoSuchElementException if there are no mappings to the
     *      specified id.
     */
    public ValueChange<IdData<T>> set(int id, T value) {
        Objects.requireNonNull(value);

        return dataMap.set(id, value);
    }


    /**
     * Returns the {@code IdData} that is mapped to the specified id.
     *
     * @param id - the id of the {@code IdData} to return.
     * @return the {@code IdData} that is mapped to the specified id.
     */
    public IdData<T> get(int id) {
        return dataMap.get(id);
    }


    /**
     * Removes the data associated with the specified ID.
     *
     * @param id - the ID of the data to remove.
     */
    public ValueChange<IdData<T>> remove(int id) {
        return dataMap.remove(id);
    }


    /**
     * Resets the ID count.
     */
    public void resetIdCount() {
        dataMap.resetIdCount();
    }


    // ===== ReadOnlyStorageModel overrides


    @Override
    public ObservableMap<Integer, IdData<T>> getMapView() {
        return dataMap.asUnmodifiableObservableMap();
    }


    // ===== Object class overrides


    @Override
    public String toString() {
        return dataMap.asUnmodifiableObservableMap().toString();
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StorageModel)) {
            return false;
        }

        StorageModel<?> castedOther = (StorageModel<?>) other;
        return dataMap.asUnmodifiableObservableMap()
                .equals(castedOther.dataMap.asUnmodifiableObservableMap());
    }


    @Override
    public int hashCode() {
        return dataMap.hashCode();
    }
}
