package seedu.address.model.client.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a premium of a policy. A premium should only contain numbers and can be either an integer or a double.
 * Provides methods to check if a given string is a valid premium amount and to construct a premium object
 * from a valid string.
 */
public class Premium {

    public static final String MESSAGE_CONSTRAINTS =
            "Premiums should only contain numbers, it can be either integer, or a double";
    public static final String VALIDATION_REGEX = "\\d{1,}.*..*\\d{0,}";
    public final String value;

    /**
     * Constructs a {@code Premium}.
     *
     * @param amount A valid premium amount.
     */
    public Premium(String amount) {
        requireNonNull(amount);
        checkArgument(isValidPremium(amount), MESSAGE_CONSTRAINTS);
        value = amount;
    }

    /**
     * Returns true if a given string is a valid premium amount
     */
    public static boolean isValidPremium(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Premium // instanceof handles nulls
                && value.equals(((Premium) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
