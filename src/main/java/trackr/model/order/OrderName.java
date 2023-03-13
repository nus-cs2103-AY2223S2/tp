package trackr.model.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents an Order's name in the order list.
 * Guaruntees: immutable; is valid as declared in {@link #isValidOrderName(String)}
 */
public class OrderName {

    public static final String MESSAGE_CONSTRAINTS =
            "Order Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the task name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEK = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code OrderName}.
     *
     * @param orderName A valid order name.
     */
    public OrderName(String orderName) {
        requireNonNull(orderName);
        checkArgument(isValidOrderName(orderName), MESSAGE_CONSTRAINTS);
        value = orderName;
    }

    /**
     * Returns true if a given string is a valid order name.
     */
    public static boolean isValidOrderName(String test) {
        return test.matches(VALIDATION_REGEK);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderName // instanceof handles nulls
                && value.equals(((OrderName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
