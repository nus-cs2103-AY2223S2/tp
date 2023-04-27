package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public static final String NULL_REMARK = "None yet!";

    public final String value;

    /**
     * Accepts remark that is not empty.
     *
     * @param remark String of a remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Initiates a null remark object without null error.
     */
    public Remark() {
        value = NULL_REMARK;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
