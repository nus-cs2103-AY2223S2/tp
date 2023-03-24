package seedu.vms.commons.core;

import java.util.Objects;
import java.util.Optional;


/**
 * Represents a change in state of a value. A change will always contain an
 * {@code oldValue} but may not contain a {@code newValue}. If it does not
 * have a {@code newValue}, ie if {@link #getNewValue()} returns
 * {@code Optional.empty}, it signifies that the change is a deletion.
 *
 * @param <T> the type of value being changed.
 */
public class ValueChange<T> {
    private final T oldValue;
    private final Optional<T> newValue;


    /**
     * Constructs a {@code ValueChange} without a new value.
     *
     * @param oldValue - the initial state of the value.
     * @throws NullPointerException if {@code oldValue} is {@code null}.
     */
    public ValueChange(T oldValue) {
        this(oldValue, null);
    }


    /**
     * Constructs a {@code ValueChange}.
     *
     * @param oldValue - the initial state of the value.
     * @param newValue - the new state of the value (can be {@code null}).
     * @throws NullPointerException if {@code oldValue} is {@code null}.
     */
    public ValueChange(T oldValue, T newValue) {
        this.oldValue = Objects.requireNonNull(oldValue);
        this.newValue = Optional.ofNullable(newValue);
    }


    /**
     * Returns the initial state of the value being changed.
     *
     * @return the initial state of the value being changed.
     */
    public T getOldValue() {
        return oldValue;
    }


    /**
     * Returns the new value wrapped in an {@code Optional}. If the change is a
     * deletion, the change will not contain a new value and
     * {@code Optional.empty} will be returned instead.
     *
     * @return the new value wrapped in an {@code Optional}.
     */
    public Optional<T> getNewValue() {
        return newValue;
    }
}
