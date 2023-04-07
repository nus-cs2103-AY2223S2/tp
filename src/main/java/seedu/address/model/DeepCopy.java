package seedu.address.model;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Any class implementing this interface can be deeply copied
 */
public interface DeepCopy<T> {
    /**
     * Creates a deep copy of the object
     *
     * @return A new object with the same contents
     */
    T copy();

    /**
     * Copies a collection of objects that implement DeepCopy
     *
     * @param collection Collection of objects to copy
     * @param <T> Type of object to copy
     * @return A new collection of copied objects
     */
    static <T> Collection<T> copyCollection(Collection<? extends DeepCopy<? extends T>> collection) {
        return collection.stream()
            .map(DeepCopy::copy)
            .collect(Collectors.toList());
    }
}
