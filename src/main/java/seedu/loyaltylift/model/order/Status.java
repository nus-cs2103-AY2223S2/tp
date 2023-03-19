package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents the possible values for an Order's Status.
 */
public enum Status {
    PENDING, PAID, SHIPPED, COMPLETED, CANCELLED;

    /**
     * Returns a {@code Status} based on the given string.
     *
     * @param status The given string.
     */
    public static Status fromString(String status) {
        requireNonNull(status);
        return valueOf(status.toUpperCase());
    }

    @Override
    public String toString() {
        String str = super.toString();
        return str.substring(0, 1).toUpperCase()
                + str.substring(1).toLowerCase();
    }
}
