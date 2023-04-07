package seedu.address.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Copies a stream of objects that implement DeepCopy
     *
     * @param stream Collection of objects to copy
     * @param <T> Type of object to copy
     * @return A new collection of copied objects
     */
    static <T> Stream<T> copyCollection(Stream<? extends DeepCopy<? extends T>> stream) {
        return stream
            .map(DeepCopy::copy);
    }
}
