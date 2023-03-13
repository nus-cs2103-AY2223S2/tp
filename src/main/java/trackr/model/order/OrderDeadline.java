package trackr.model.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Order's deadline in the order list.
 * Guarantees: immutable; is valid as declared in {@link #isValidOrderDeadline(String)}
 */
public class OrderDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Order deadline should only contain numeric values in the format \"DD/MM/YYYY\""
                    + "and it should not be blank"
                    + "and deadline should be today or after today's date.";

    public static final String VALIDATION_REGEX = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";

    private static final DateTimeFormatter DTF_INPUT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DTF_OUTPUT_DATE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public final LocalDate orderDeadline;

    public OrderDeadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidOrderDeadline(deadline), MESSAGE_CONSTRAINTS);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        orderDeadline = LocalDate.parse(deadline, dtf);
    }

    public static boolean isValidOrderDeadline(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDate.parse(test, DTF_INPUT_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Returns the deadline stored in "dd/MM/yyyy" format for json storage.
     * @return A string representation of the deadline.
     */
    public String toJsonString() {
        return orderDeadline.format(DTF_INPUT_DATE);
    }

    /**
     * Returns the deadline stored in "01 JANUARY 2023" format.
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return orderDeadline.format(DTF_OUTPUT_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDeadline // instanceof handles nulls
                && orderDeadline.equals(((OrderDeadline) other).orderDeadline)); // state check
    }

    @Override
    public int hashCode() {
        return orderDeadline.hashCode();
    }
}
