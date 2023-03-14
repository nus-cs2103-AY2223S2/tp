package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Price of an Expense in FastTrack.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only contain numbers, and should not be negative";
    public static final String VALIDATION_REGEX = "^(0|[1-9]\\d*)(\\.\\d+)?$";
    public final String value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public double getPriceAsDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

