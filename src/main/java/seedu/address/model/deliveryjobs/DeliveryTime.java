package seedu.address.model.deliveryjobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's time in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 * @deprecated Refine and merge with jobs.DeliveryJob when timetable design is ready.
 */
@Deprecated
public class DeliveryTime {

    public static final String MESSAGE_CONSTRAINTS = "DeliveryTime can take any character, and it should not be blank";

    public static final String VALIDATION_REGEX = ".";

    public final String value;

    /**
     * Constructs an {@code delivery time}.
     *
     * @param deliveryTime A valid delivery time.
     */
    public DeliveryTime(String deliveryTime) {
        requireNonNull(deliveryTime);
        checkArgument(isValidTime(deliveryTime), MESSAGE_CONSTRAINTS);
        value = deliveryTime;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.deliveryjobs.DeliveryTime // instanceof handles nulls
                && value.equals(((seedu.address.model.deliveryjobs.DeliveryTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
