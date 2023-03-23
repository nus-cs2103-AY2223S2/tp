package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

/**
 * Represents a mono-field class that all fields in Person should inherit from.
 */
public abstract class Field {

    public final String value;

    /**
     * Default field constructor.
     */
    public Field(String value) {
        requireNonNull(value);
        this.value = value;
    }

    /**
     * Returns true if test string is contained within the value of the field.
     */
    public boolean contains(String test) {
        return this.value.toUpperCase().contains(test.toUpperCase());
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
