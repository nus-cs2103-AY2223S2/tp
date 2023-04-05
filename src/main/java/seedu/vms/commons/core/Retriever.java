package seedu.vms.commons.core;

import java.util.List;
import java.util.Map;

import seedu.vms.commons.exceptions.IllegalValueException;


/**
 * Represents a retriever of data from its key in a map or its index in a list.
 *
 * @param <K> the type of the key the data may be mapped to.
 * @param <V> the type of the data being retrieved.
 */
public abstract class Retriever<K, V> {
    /**
     * Creates a {@code Retriever} that will retrieve the data mapped to the
     * specified key.
     *
     * @param key - the key of the data to retrieve.
     * @return a {@code Retriever} that retrieves data from maps.
     */
    public static <K, V> Retriever<K, V> of(K key) {
        return new Retriever<K, V>() {
            @Override
            public V retrieve(Map<? extends K, V> map, List<V> list) throws IllegalValueException {
                return retrieveFromMap(map, key);
            }
        };
    }


    /**
     * Creates a {@code Retriever} that will retrieve the data at the specified
     * index in a list.
     *
     * @param index - the index of the data in a list to retrieve.
     * @return a {@code Retriever} that retrieves data from lists.
     */
    public static <K, V> Retriever<K, V> of(int index) {
        return new Retriever<K, V>() {
            @Override
            public V retrieve(Map<? extends K, V> map, List<V> list) throws IllegalValueException {
                return retrieveFromList(list, index);
            }
        };
    }


    private static <K, V> V retrieveFromMap(Map<? extends K, V> map, K key) throws IllegalValueException {
        if (!map.containsKey(key)) {
            throw new IllegalValueException(String.format("%s does not exist", key.toString()));
        }
        return map.get(key);
    }


    private static <V> V retrieveFromList(List<V> list, int index) throws IllegalValueException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException oobEx) {
            throw new IllegalValueException(String.format("Index %d out of bounds for length %d",
                    index + 1, list.size()));
        }
    }


    /**
     * Retrieves data from the given data structures. The data structure that
     * the retriever retrieves from is determined by the retriever.
     *
     * @param map - the map from which data may be retrieved from.
     * @param list - the list from which data may be retrieved from.
     * @throws IllegalValueException if the data cannot be retrieved.
     */
    public abstract V retrieve(Map<? extends K, V> map, List<V> list) throws IllegalValueException;
}
