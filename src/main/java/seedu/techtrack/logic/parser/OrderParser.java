package seedu.techtrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job's company name in the role book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOrder(String)}
 */
public class OrderParser {

    public static final String MESSAGE_CONSTRAINTS =
            "Order should only be asc or desc in lower caps";

    private String order;

    /**
     * Constructs a {@code Order}.
     *
     * @param order A valid order.
     */
    public OrderParser(String order) {
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
