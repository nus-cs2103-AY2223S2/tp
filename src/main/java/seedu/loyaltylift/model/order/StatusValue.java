package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Enum that represents the stage of an Order.
 */
public enum StatusValue {
    PENDING, PAID, SHIPPED, COMPLETED, CANCELLED;

    public static final String MESSAGE_FAIL_CONVERSION = "Unrecognised order type";

    /**
     * Returns a {@code StatusValue} based on the given string.
     *
     * @param status The given string.
     */
    public static StatusValue fromString(String status) {
        requireNonNull(status);
        return valueOf(status.toUpperCase());
    }

    /**
     * Returns a {@code StatusValue} that is the next logical stage of an Order.
     * The logical order of these stages is defined as the order of the
     * enum values above.
     * Should not be called when StatusValue is Pending or Cancelled.
     * @return A {@code StatusValue}
     */
    public StatusValue nextValue() {
        if (this.equals(COMPLETED) || this.equals(CANCELLED)) {
            throw new IllegalStateException();
        }
        int pos = this.ordinal();
        return StatusValue.values()[pos + 1];
    }

    @Override
    public String toString() {
        String str = super.toString();
        return str.substring(0, 1).toUpperCase()
                + str.substring(1).toLowerCase();
    }
}
