package mycelium.mycelium.model.util;

import static mycelium.mycelium.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Basically a wrapper around String that enforces the invariant that the string is non-empty.
 */
public class NonEmptyString {
    private final String value;

    public static final String MESSAGE_CONSTRAINTS = "String cannot be null or empty";

    /**
     * Creates a new NonEmptyString from the given value. If the value is null or empty, an IllegalArgumentException
     * is thrown.
     *
     * @param value the value to wrap
     */
    public NonEmptyString(String value) {
        // NOTE: isValid will check if it is null, thus we don't need to call requireNonNull
        checkArgument(isValid(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns the value of this NonEmptyString.
     */
    public String getValue() {
        return value;
    }

    /**
     * Convenience method to create a new NonEmptyString from the given value.
     */
    public static NonEmptyString of(String value) {
        return new NonEmptyString(value);
    }

    public static boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NonEmptyString that = (NonEmptyString) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
