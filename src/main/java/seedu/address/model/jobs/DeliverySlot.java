package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's Delivery Slot in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidDeliverySlot(String)}
 */
public class DeliverySlot {

    public static final String MESSAGE_CONSTRAINTS = "Delivery Slot should between [1-n], and it should not be blank";

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
     * Returns true if a given string is a valid earning.
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
            return "10am - 11am";
        case "2":
            return "11am - 12pm";
        case "3":
            return "1pm  - 2pm ";
        case "4":
            return "2pm  - 3pm ";
        case "5":
            return "3pm  - 4pm ";
        default:
            return "N.A";
        }
    }

}
