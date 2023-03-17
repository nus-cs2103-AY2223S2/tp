package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's Delivery Slot in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliverySlot(String)}
 */
public class DeliverySlot {

    public static final String MESSAGE_CONSTRAINTS = "Delivery Slot should between [1-n], and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code earning}.
     *
     * @param value A valid earning.
     */
    public DeliverySlot(String value) {
        requireNonNull(value);
        checkArgument(isValidDeliverySlot(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid earning.
     */
    public static boolean isValidDeliverySlot(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliverySlot // instanceof handles nulls
                && value.equals(((DeliverySlot) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
