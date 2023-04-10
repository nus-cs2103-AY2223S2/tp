package seedu.vms.commons.core;

import java.util.Optional;


/**
 * Represents a change in state of a value.
 *
 * @param <T> the type of value being changed.
 */
public class ValueChange<T> {
    public static final String MESSAGE_ADDITION = "%s added";
    public static final String MESSAGE_DELETION = "%s deleted";
    public static final String MESSAGE_UPDATE = "%s updated";
    public static final String MESSAGE_NO_CHANGE = "Nothing changed";

    private final Optional<T> oldValue;
    private final Optional<T> newValue;


    /**
     * Constructs a {@code ValueChange}.
     *
     * @param oldValue - the initial state of the value.
     * @param newValue - the new state of the value (can be {@code null}).
     */
    public ValueChange(T oldValue, T newValue) {
        this.oldValue = Optional.ofNullable(oldValue);
        this.newValue = Optional.ofNullable(newValue);
    }


    /**
     * Returns the old value wrapped in an {@code Optional}. If the change is
     * only an addition, the change will not contain an old value and
     * {@code Optional.empty} will be returned instead.
     *
     * @return the initial state of the value being changed wrapped in an
     *      {@code Optional}.
     */
    public Optional<T> getOldValue() {
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


    @Override
    public String toString() {
        if (oldValue.equals(newValue)) {
            return MESSAGE_NO_CHANGE;
        } else if (oldValue.isPresent() && newValue.isPresent()) {
            return String.format(MESSAGE_UPDATE, oldValue.get().toString());
        } else if (oldValue.isPresent()) {
            return String.format(MESSAGE_DELETION, oldValue.get().toString());
        } else {
            return String.format(MESSAGE_ADDITION, newValue.get().toString());
        }
    }
}
