package trackr.model.order;

import trackr.model.commons.Deadline;

/**
 * Represents an Order's deadline in the order list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class OrderDeadline extends Deadline {
    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    public static final String MESSAGE_CONSTRAINTS =
            "Order deadline should only contain numeric values "
                    + "in the format \"DD/MM/YYYY\" and it should not be blank.";
    //@@author


    /**
     * Constructs a {@code OrderDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public OrderDeadline(String deadline) {
        super(deadline, "Order");
    }
}
