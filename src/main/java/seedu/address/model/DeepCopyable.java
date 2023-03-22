package seedu.address.model;

/**
 * Represents that an implementing Object can be deep (mutation-independently) copied into a {@code T}.
 *
 * @param <T> The type to copy into
 */
public interface DeepCopyable<T> {
    public T deepCopy();
}
