package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's Delivery Slot in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidDeliverySlot(String)}
 */
public class DeliverySlot {

    public static final String MESSAGE_CONSTRAINTS = "Delivery Slot must be larger than 0 - should be between [1-5]."
            + "\nIt should not be blank";

    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code DeliverySlot}.
     *
     * @param value A valid slot.
     */
    public DeliverySlot(String value) {
        requireNonNull(value);
        checkArgument(isValidDeliverySlot(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Constructs an {@code DeliverySlot}.
     */
    private DeliverySlot() {
        this.value = "-1";
    }

    /**
     * Returns true if a given string is a valid slot.
     */
    public static boolean isValidDeliverySlot(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    /**
     * Returns slot value in integer
     */
    public int getSlot() {
        return Integer.parseInt(value);
    }

    /**
     * Checks if delivery slot is valid (within range 1-5)
     * @return
     */
    public boolean isValid() {
        return ((Integer.parseInt(value) < 6) && (Integer.parseInt(value) > 0));
    }

    /**
     * Checks if delivery slot is positive (larger than 0)
     * @return
     */
    public boolean isPositive() {
        return Integer.parseInt(value) > 0;
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

    public static DeliverySlot placeholder() {
        return new DeliverySlot();
    }

    public String getDescription() {
        switch (value) {
        case "1":
            return "10AM - 11AM";
        case "2":
            return "11AM - 12PM";
        case "3":
            return "1PM  - 2PM";
        case "4":
            return "2PM  - 3PM";
        case "5":
            return "3PM  - 4PM";
        default:
            if (Integer.parseInt(value) > 5) {
                return "Extra hours (4PM++)";
            }
            return "N.A.";
        }
    }

}
