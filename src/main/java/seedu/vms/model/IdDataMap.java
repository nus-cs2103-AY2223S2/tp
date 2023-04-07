package seedu.vms.model;

import java.util.Collection;
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
     * @return the data added.
     * @throws LimitExceededException if the number of data has reached its
     *      limit.
     */
    public IdData<T> add(T value) throws LimitExceededException {
        Objects.requireNonNull(value);
        IdData<T> data = new IdData<>(getNextId(), value);
        nextId++;
        return add(data);
    }


    /**
     * Adds the data to the map. If there is already a mapping to the given
     * data's ID, that ID associated value is replaced with the given.
     *
     * @param data - the data to add.
     * @return the data added.
     * @throws LimitExceededException if the ID limit has been reached.
     * @throws IllegalArgumentException if the given ID of the data is invalid.
     */
    public IdData<T> add(IdData<T> data) throws LimitExceededException {
        Objects.requireNonNull(data);
        if (!isWithinLimit(data.getId())) {
            // if ID exceeds limit
            throw new LimitExceededException(String.format(Messages.FORMAT_LIMIT_EX, limit));
        } else if (!isValidId(data.getId())) {
            // all other cases of invalid ID
            throw new IllegalArgumentException("Invalid ID");
        }
        internalMap.put(data.getId(), data);
        nextId = Math.max(nextId, data.getId() + 1);
        return data;
    }


    /**
     * Sets the specified ID's value to the given value. If there is already a
     * mapping to the given ID, that ID associated value is replaced with the
     * given.
     *
     * @param id - the id to set.
     * @param value - the value to set.
     * @return the {@code ValueChange} that describes the change that
     *      has occurred.
     */
    public ValueChange<IdData<T>> set(int id, T value) {
        IdData<T> newValue = new IdData<>(id, value);
        IdData<T> oldValue = internalMap.put(id, newValue);
        return new ValueChange<>(oldValue, newValue);
    }


    /**
     * Removes the data associated to the specified ID.
     *
     * @param id - the ID of the data to remove.
     * @return the {@code ValueChange} that describes the change that
     *      has occurred.
     */
    public ValueChange<IdData<T>> remove(int id) {
        IdData<T> removedData = internalMap.remove(id);
        return new ValueChange<>(removedData, null);
    }


    /**
     * Resets the ID count.
     */
    public void resetIdCount() {
        nextId = STARTING_INDEX;
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
     * @throws LimitExceededException if there exists a data whose ID is over
     *      the limit of this {@code IdDataMap}.
     * @throws IllegalArgumentException if there exists a data whose ID is
     *      invalid.
     */
    public void setDatas(Collection<IdData<T>> datas) {
        internalMap.clear();
        nextId = STARTING_INDEX;
        for (IdData<T> data : datas) {
            add(data);
        }
    }


    /**
     * Clears and sets the stored value to the given collection of values.
     *
     * <p>The ID will be reset as well.
     *
     * @param values - the collection of values to set to.
     * @throws LimitExceededException if the limit has been reached. The first
     *      few values before the limit is reached will still be added.
     */
    public void setValues(Collection<T> values) {
        internalMap.clear();
        nextId = STARTING_INDEX;
        for (T value : values) {
            add(value);
        }
    }


    /**
     * Returns the data mapped to the specified ID or {@code null} if there are
     * no mappings.
     */
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
        while (contains(nextId) || !isValidId(nextId)) {
            if (isValidId(nextId)) {
                nextId++;
            } else {
                nextId = 0;
            }
        }
        return nextId;
    }


    /** Returns if the given ID is valid. */
    private boolean isValidId(int id) {
        return 0 <= id && id < limit;
    }


    /** Returns if the given ID is within the set limit. */
    private boolean isWithinLimit(int id) {
        return id < limit;
    }
}
