package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Pay Rate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPayRate(int)}
 */
public class PayRate {

    public static final String MESSAGE_CONSTRAINTS = "Pay Rate should be a non-negative integer, representing pay per hour.";
    public static final String VALIDATION_REGEX = "^[0-9]+$";

    public final String value;

    /**
     * Constructs an {@code PayRate}.
     *
     * @param pay A valid pay rate.
     */
    public PayRate(String pay) {
        requireNonNull(pay);
        checkArgument(isValidPayRate(pay), MESSAGE_CONSTRAINTS);
        value = pay;
    }

    /**
     * Returns if a given integer is a valid pay rate.
     */
    public static boolean isValidPayRate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PayRate // instanceof handles nulls
                && value.equals(((PayRate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
