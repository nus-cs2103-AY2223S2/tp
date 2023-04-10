package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

import java.util.Set;

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
     * Returns true if at least one of the strings contained in test
     * is contained within the value of the field.
     *
     * For the more mathematically inclined,
     * {∃t ∈ test s.t. this.value.toUpperCase().contains(t.toUpperCase()) == true}.
     */
    public boolean contains(Set<String> test) {
        for (String t: test) {
            if (t.isEmpty()) {
                continue;
            }
            if (this.value.toUpperCase().contains(t.toUpperCase())) {
                return true;
            }
        }
        return false;
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
