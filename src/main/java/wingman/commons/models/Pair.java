package wingman.commons.models;

/**
 * Represents a pair of objects.
 *
 * @param <A> the type of the first object
 * @param <B> the type of the second object
 */
public class Pair<A, B> {
    private final A first;
    private final B second;

    /**
     * Creates a pair of objects.
     *
     * @param first  the first object
     * @param second the second object
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first object.
     *
     * @return the first object
     */
    public A getFirst() {
        return first;
    }

    /**
     * Returns the second object.
     *
     * @return the second object
     */
    public B getSecond() {
        return second;
    }
}
