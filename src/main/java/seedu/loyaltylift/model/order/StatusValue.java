package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents the possible values for an Order's Status.
 */
public enum StatusValue {
    PENDING, PAID, SHIPPED, COMPLETED, CANCELLED;

    public static final String MESSAGE_FAIL_CONVERSION = "Unrecognised status";

    /**
     * Returns a {@code StatusValue} based on the given string.
     *
     * @param name The given string.
     */
    public static StatusValue findByName(String name) {
        StatusValue result = null;
        for (StatusValue status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                result = status;
                break;
            }
        }
        return result;
    }

    public static StatusValue fromUserFriendlyString(String statusValueString) {
        requireNonNull(statusValueString);

        String uStatusValueString = statusValueString.toUpperCase();
        switch (uStatusValueString) {
        case "PENDING":
            return StatusValue.PENDING;
        case "PAID":
            return StatusValue.PAID;
        case "COMPLETED":
            return StatusValue.COMPLETED;
        case "SHIPPED":
            return StatusValue.SHIPPED;
        case "CANCELLED":
            return StatusValue.CANCELLED;
        default:
            return valueOf(uStatusValueString);
        }
    }

    @Override
    public String toString() {
        String str = super.toString();
        return str.substring(0, 1).toUpperCase()
                + str.substring(1).toLowerCase();
    }
}
