package fasttrack.model.expense;

import static java.util.Objects.requireNonNull;

import fasttrack.logic.commands.exceptions.CommandException;
/**
 * Represents a Price of an Expense in FastTrack.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only contain numbers, and should not be negative";
    public static final String VALIDATION_REGEX = "^(0|[1-9]\\d*)(\\.\\d+)?$";
    private String value;

    /**
     * Constructs a {@code Price}.
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        value = price;
    }
    /**
     * Constructs a {@code Price}.
     * @param price A valid price.
     */
    public Price(double price) {
        requireNonNull(price);
        if (!(price >= -0.0)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        value = String.valueOf(price);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX) && Double.parseDouble(test) >= -0;
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

    public String getValue() {
        return value;
    }

    public void setValue(String price) throws CommandException {
        if (!isValidPrice(price)) {
            throw new CommandException(MESSAGE_CONSTRAINTS);
        }
        value = price;
    }

}

