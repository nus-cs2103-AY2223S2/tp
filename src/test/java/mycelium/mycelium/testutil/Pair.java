package mycelium.mycelium.testutil;

import java.util.Objects;

/**
 * A generic pair class.
 *
 * @param <A> Type of the first item.
 * @param <B> Type of the second item.
 */
public class Pair<A, B> {
    public final A first;
    public final B second;

    /**
     * Creates a new {@code Pair} from two items.
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new {@code Pair}. Just a convenience method.
     */
    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
            "first=" + first +
            ", second=" + second +
            '}';
    }
}
