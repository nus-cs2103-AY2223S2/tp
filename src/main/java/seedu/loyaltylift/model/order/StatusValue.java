package seedu.loyaltylift.model.order;

/**
 * Represents the possible values for an Order's Status.
 */
public enum StatusValue {
    PENDING, PAID, SHIPPED, COMPLETED, CANCELLED;

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

    @Override
    public String toString() {
        String str = super.toString();
        return str.substring(0, 1).toUpperCase()
                + str.substring(1).toLowerCase();
    }
}
