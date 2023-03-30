package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's price in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class ItemPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain positive numbers, and it should be at most 2 decimal place";
    public static final String VALIDATION_REGEX = "^\\d+(.\\d{0,2})?$";
    public final Double value;

    /**
     * Constructs a Price Object
     */
    public ItemPrice(String value) {
        requireNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "$" + value;
    }

    public String toJsonString() {
        return Double.toString(value);
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemPrice // instanceof handles nulls
                && value == ((ItemPrice) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
