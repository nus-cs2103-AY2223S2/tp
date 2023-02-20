package seedu.vms.model;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


/**
 * Represents a map to store ID - data value pairs.
 *
 * <p>ID of values are auto generated within the class.
 *
 * @param <T> - type of data stored.
 */
public class IdDataMap<T> {
    private static final int STARTING_INDEX = 0;

    private final ObservableMap<Integer, IdData<T>> internalMap;
    private final ObservableMap<Integer, IdData<T>> internalUnmodifiableMap;

    private int nextId = STARTING_INDEX;


    /** Constructs an empty {@code IdDataMap}. */
    public IdDataMap() {
        internalMap = FXCollections.observableHashMap();
        internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);
    }


    /**
     * Adds the value to the map.
     *
     * @param value - the value to add.
     */
    public void add(T value) {
        Objects.requireNonNull(value);
        IdData<T> data = new IdData<>(nextId, value);
        add(data);
        nextId++;
    }


    /**
     * Adds the data to the map. If there is already a mapping to the given
     * data's ID, that ID associated value is replaced with the given.
     *
     * @param data - the data to add.
     */
    public void add(IdData<T> data) {
        Objects.requireNonNull(data);
        internalMap.put(data.getId(), data);
    }


    /**
     * Sets the specified ID's value to the given value. If there is already a
     * mapping to the given ID, that ID associated value is replaced with the
     * given.
     *
     * @param id - the id to set.
     * @param value - the value to set.
     * @throws NoSuchElementException if the ID is not present.
     */
    public void set(int id, T value) {
        if (!internalMap.containsKey(id)) {
            // TODO: this exception is unhandled by utilising methods.
            throw new NoSuchElementException(String.format("ID [%d] not found", id));
        }
        IdData<T> data = new IdData<>(id, value);
        internalMap.put(id, data);
    }


    /**
     * Removes the data associated to the specified ID.
     *
     * @param id - the ID of the data to remove.
     * @throws NoSuchElementException if the ID is not present.
     */
    public IdData<T> remove(int id) {
        if (!internalMap.containsKey(id)) {
            // TODO: this exception is unhandled by utilising methods.
            throw new NoSuchElementException(String.format("ID [%d] not found", id));
        }
        return internalMap.remove(id);
    }


    /**
     * Returns if there is a mapping to the specified ID.
     *
     * @param id - the id to check.
     * @return {@code true} if a mapping is present to the ID and {@code false}
     *      otherwise.
     */
    public boolean containts(int id) {
        return internalMap.containsKey(id);
    }


    /**
     * Clears and sets the stored data to the given collection of datas.
     *
     * @param datas - the collection of data to set to.
     */
    public void setDatas(Collection<IdData<T>> datas) {
        // TODO: Check for duplicate ID
        internalMap.clear();
        nextId = STARTING_INDEX;
        for (IdData<T> data : datas) {
            int id = data.getId();
            if (id >= nextId) {
                nextId = id + 1;
            }
            internalMap.put(id, data);
        }
    }


    /**
     * Clears and sets the stored value to the given collection of values.
     *
     * <p>The ID is reseted as well.
     *
     * @param values - the collection of values to set to.
     */
    public void setValues(Collection<T> values) {
        internalMap.clear();
        nextId = STARTING_INDEX;
        for (T value : values) {
            add(value);
        }
    }


    /**
     * Returns an unmodifiable map view of this data map.
     *
     * @return an unmodifiable map view of this data map.
     */
    public ObservableMap<Integer, IdData<T>> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }
}
