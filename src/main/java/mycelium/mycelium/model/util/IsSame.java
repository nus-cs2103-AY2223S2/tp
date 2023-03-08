package mycelium.mycelium.model.util;

/**
 * Represents something which requires a different notion from Java's {@code equals}.
 */
public interface IsSame<T> {
    boolean isSame(T other);
}
