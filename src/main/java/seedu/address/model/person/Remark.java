package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents optional remarks that people can add in their address book.
 */
public class Remark {

    public final String value;

    /**
     * Constructs an {@code Remark}.
     * @param remark An optional remark.
     */
    public Remark(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark// instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
