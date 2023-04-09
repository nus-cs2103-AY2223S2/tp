package trackr.model.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents the Order's Quantity
 * Guaruntees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class OrderQuantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers, and it should be at most 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public final String value;

    /**
     * Constructs an Order Quantity Object.
     */
    public OrderQuantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns true if a given string matches the validation regex for quantity.
     *
     * @param test the string to test for validity as a quantity value
     * @return true if test is a valid quantity value, false otherwise
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a quantity value in the form of an int from a string value.
     *
     * @return {@code int} form of value string.
     */
    public int getOrderQuantity() {
        return Integer.parseInt(value);
    }

    /**
     * Returns a quantity value in the form of a string.
     *
     * @return value in the form of a string.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderQuantity // instanceof handles nulls
                && value.equals(((OrderQuantity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
