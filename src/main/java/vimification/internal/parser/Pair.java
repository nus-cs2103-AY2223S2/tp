package vimification.internal.parser;

import java.util.function.Function;

/**
 * Represents a tuple of two elements.
 *
 * @param <T> the type of the first element
 * @param <U> the type of the second element
 */
public class Pair<T, U> {

    private T first;
    private U second;

    /**
     * Creates a new pair of two elements.
     *
     * @param first the first element
     * @param second the second element
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new pair of two elements. This is a factory method, used to reduce the verbosity
     * when creating a new pair.
     *
     * @param <T> the type of the first element
     * @param <U> the type of the second element
     * @param first the first element
     * @param second the second element
     * @return a new pair that contains the two elements
     */
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public <V> Pair<V, U> mapFirst(Function<? super T, ? extends V> mapper) {
        return of(mapper.apply(first), second);
    }

    public <R> Pair<T, R> mapSecond(Function<? super U, ? extends R> mapper) {
        return of(first, mapper.apply(second));
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", first, second);
    }
}
