package seedu.address.commons.util;

import java.util.Objects;

/**
 * Pair of 2 objects
 *
 * @param <T> Type of first object
 * @param <U> Type of second object
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * @param first  First object
     * @param second Second object
     */
    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @param first  First object
     * @param second Second object
     * @return Pair of first and second object
     */
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    /**
     * @return First object
     */
    public T first() {
        return this.first;
    }

    /**
     * @return Second object
     */
    public U second() {
        return this.second;
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>",
                this.first.toString(),
                this.second.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pair)) {
            return false;
        }

        Pair<?, ?> otherPair = (Pair<?, ?>) other;
        return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
