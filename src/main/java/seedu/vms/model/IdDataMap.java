package seedu.vms.model;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.LimitExceededException;


/**
 * Represents a map to store ID - data value pairs.
 *
 * <p>ID of values are auto generated within the class.
 *
 * @param <T> - type of data stored.
 */
public class IdDataMap<T> {
    public static final int DEFAULT_LIMIT = 1000;

    private static final int STARTING_INDEX = 0;

    private final int limit;
    private final ObservableMap<Integer, IdData<T>> internalMap;
    private final ObservableMap<Integer, IdData<T>> internalUnmodifiableMap;

    private int nextId = STARTING_INDEX;


    /** Constructs an empty {@code IdDataMap} that uses the default limit. */
    public IdDataMap() {
        this(DEFAULT_LIMIT);
    }


    /**
     * Constructs an empty {@code IdDataMap}.
     *
     * @param limit - the size limit of this data map.
     */
    public IdDataMap(int limit) {
        this.limit = limit;
        internalMap = FXCollections.observableHashMap();
        internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);
    }


    /**
     * Adds the value to the map.
     *
     * @param value - the value to add.
     * @throws LimitExceededException if the number of data has reached its
     *      limit.
     */
    public IdData<T> add(T value) throws LimitExceededException {
        Objects.requireNonNull(value);
        IdData<T> data = new IdData<>(getNextId(), value);
        return add(data);
    }


    /**
     * Adds the data to the map. If there is already a mapping to the given
     * data's ID, that ID associated value is replaced with the given.
     *
     * @param data - the data to add.
     */
    public IdData<T> add(IdData<T> data) throws LimitExceededException {
        Objects.requireNonNull(data);
        if (!isValidId(data.getId())) {
            throw new LimitExceededException(String.format(Messages.FORMAT_LIMIT_EX, limit));
        }
        internalMap.put(data.getId(), data);
        nextId = Math.max(nextId, data.getId());
        return data;
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
    public ValueChange<IdData<T>> set(int id, T value) {
        if (!internalMap.containsKey(id)) {
            // TODO: this exception is unhandled by utilising methods.
            throw new NoSuchElementException(String.format("ID [%d] not found", id));
        }
        IdData<T> newValue = new IdData<>(id, value);
        IdData<T> oldValue = internalMap.put(id, newValue);
        return new ValueChange<>(oldValue, newValue);
    }


    /**
     * Removes the data associated to the specified ID.
     *
     * @param id - the ID of the data to remove.
     */
    public ValueChange<IdData<T>> remove(int id) {
        IdData<T> removedData = internalMap.remove(id);
        if (internalMap.isEmpty()) {
            nextId = 0;
        }
        return new ValueChange<>(removedData, null);
    }


    /**
     * Returns if there is a mapping to the specified ID.
     *
     * @param id - the id to check.
     * @return {@code true} if a mapping is present to the ID and {@code false}
     *      otherwise.
     */
    public boolean contains(int id) {
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
            add(data);
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


    public IdData<T> get(int id) {
        return internalMap.get(id);
    }


    /**
     * Returns an unmodifiable map view of this data map.
     *
     * @return an unmodifiable map view of this data map.
     */
    public ObservableMap<Integer, IdData<T>> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }


    private int getNextId() throws LimitExceededException {
        if (internalMap.size() >= limit) {
            throw new LimitExceededException(String.format(Messages.FORMAT_LIMIT_EX, limit));
        }
        while (contains(nextId)) {
            nextId++;
            if (!isValidId(nextId)) {
                nextId = 0;
            }
        }
        return nextId;
    }


    private boolean isValidId(int id) {
        return 0 <= id && id < limit;
    }
}
