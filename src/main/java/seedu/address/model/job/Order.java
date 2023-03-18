package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job's company name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOrder(String)}
 */
public class Order {

    public static final String MESSAGE_CONSTRAINTS =
            "Order should only be asc or desc in lower caps";

    public String order;

    /**
     * Constructs a {@code Order}.
     *
     * @param order A valid order.
     */
    public Order(String order) {
        requireNonNull(order);
        checkArgument(isValidOrder(order), MESSAGE_CONSTRAINTS);
        this.order = order;
    }

    /**
     * Returns true if a given string is a valid order.
     */
    public static boolean isValidOrder(String test) {
        return test.equals("asc") || test.equals("desc");
    }


    @Override
    public String toString() {
        return order;
    }

    @Override
    public int hashCode() {
        return order.hashCode();
    }

}
