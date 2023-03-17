package trackr.model.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents an Order's status in the order list.
 * Guaruntees: immutable; is valid as declared in {@link #isValidOrderStatus(String)}
 */
public class OrderStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Order status should only be `N` or `n` for Not Delivered, `I` or `i` for In Progress,"
            + " or `D` or `d` for Delivered";

    /*
     * The first character of the task status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[nNiIdD]$";

    private String orderStatus;

    /**
     * Constructs a {@code OrderStatus}.
     */
    public OrderStatus() {
        //Order status defaulted to false
        orderStatus = "N";
    }
    /**
     * Constructs a {@code OrderStatus}.
     */
    public OrderStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidOrderStatus(status), MESSAGE_CONSTRAINTS);
        if (status.equalsIgnoreCase("D")) {
            orderStatus = "D";
        } else if (status.equalsIgnoreCase("I")) {
            orderStatus = "I";
        } else if (status.equalsIgnoreCase("N")) {
            orderStatus = "N";
        }
    }

    /**
     * Returns true if a given string is a valid order status.
     */
    public static boolean isValidOrderStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toJsonString() {
        return orderStatus;
    }

    @Override
    public String toString() {
        if (orderStatus == "N") {
            return "Not Delivered";
        } else if (orderStatus == "I") {
            return "In Progress";
        } else {
            return "Delivered";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderStatus // instanceof handles nulls
                && orderStatus == ((OrderStatus) other).orderStatus); // state check
    }

    @Override
    public int hashCode() {
        return orderStatus.hashCode();
    }

}
