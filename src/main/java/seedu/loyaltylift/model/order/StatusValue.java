package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;

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

    public StatusValue nextValue() {
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