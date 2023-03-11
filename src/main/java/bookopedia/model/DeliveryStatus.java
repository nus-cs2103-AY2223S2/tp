package bookopedia.model;

/**
 * Delivery status.
 */
public enum DeliveryStatus {
    PENDING, OTW, DONE, FAILED;

    public static final String MESSAGE_CONSTRAINTS = "Delivery status must be of the following: "
            + "pending, otw, done, failed";
}
