package mycelium.mycelium.model.util;

import java.util.Objects;

/**
 * Basically a wrapper around String that enforces the invariant that the string is non-empty.
 */
public class NonEmptyString {
    private final String value;

    /**
     * Creates a new NonEmptyString from the given value. If the value is null or empty, an IllegalArgumentException
     * is thrown.
     *
     * @param value the value to wrap
     */
    public NonEmptyString(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("String cannot be null or empty");
        }
        this.value = value;
    }

    /**
     * Returns the value of this NonEmptyString.
     */
    public String getValue() {
        return value;
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
