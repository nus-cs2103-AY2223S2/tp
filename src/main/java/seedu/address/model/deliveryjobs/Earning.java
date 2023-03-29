package seedu.address.model.deliveryjobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's earning in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEarning(String)}
 * @deprecated moved to jobs
 */
@Deprecated
public class Earning {

    public static final String MESSAGE_CONSTRAINTS = "Earning should only contain double, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d+\\.\\d+";

    public final String value;

    /**
     * Constructs an {@code earning}.
     *
     * @param earning A valid earning.
     */
    public Earning(String earning) {
        requireNonNull(earning);
        checkArgument(isValidEarning(earning), MESSAGE_CONSTRAINTS);
        value = earning;
    }

    /**
     * Returns true if a given string is a valid earning.
     */
    public static boolean isValidEarning(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.deliveryjobs.Earning // instanceof handles nulls
                && value.equals(((seedu.address.model.deliveryjobs.Earning) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
