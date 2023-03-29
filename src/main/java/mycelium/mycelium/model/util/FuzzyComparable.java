package mycelium.mycelium.model.util;

/**
 * An interface for objects that can be compared in a fuzzy way. Somewhat analogous to {@code Comparable}, but
 * instead of returning an integer, it returns a double between 0 and 1.
 *
 * @param <T> The type of the object to compare to.
 */
public interface FuzzyComparable<T> {
    /**
     * Retrieves a string that will be used for fuzzy matching.
     */
    String getFuzzyField();
}
