package mycelium.mycelium.model.util;

/**
 * An interface for objects that can be compared in a fuzzy way. Somewhat analogous to {@code Comparable}, but
 * instead of returning an integer, it returns a double between 0 and 1.
 *
 * @param <T> The type of the object to compare to.
 */
public interface FuzzyComparable<T> {
    /**
     * Returns a value between 0 and 1, where 0 means the two objects are
     * completely different, and 1 means they are completely the same.
     *
     * @param other The other object to compare to.
     * @return A value between 0 and 1.
     */
    double fuzzyCompareTo(T other);
}
