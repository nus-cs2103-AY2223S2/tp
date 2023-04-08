package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the remark book.
 * Guarantees: immutable;
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values, but it should not be blank.";
    public final String value;

    /**
     * Constructs an {@code remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid company.
     */
    public static boolean isValidRemark(String test) {
        return !test.strip().isEmpty();
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
