package bookopedia.model;

/**
 * Delivery status.
 */
public enum DeliveryStatus {
    PENDING, OTW, DONE, FAILED, RETURN;

    public static final int NO_OF_ATTEMPTS_BEFORE_RETURN = 3;

    public static final String MESSAGE_CONSTRAINTS = "Delivery status must be of the following: "
            + "pending, otw, done, failed";

    public static final String MESSAGE_CONSTRAINTS_NEGATIVE_ATTEMPTS = "No. of delivery attempts cannot be negative!";

    public static final String MESSAGE_CONSTRAINTS_RETURN_ATTEMPTS = String.format("No. of delivery attempts cannot "
            + "be more than 'no. of attempts before return' which is %d!", NO_OF_ATTEMPTS_BEFORE_RETURN);

    public static final String MESSAGE_CONSTRAINTS_RETURN_MISMATCH = String.format("No. of delivery attempts is equal "
            + "to the 'no.of attempts before return' but the status is not RETURN", NO_OF_ATTEMPTS_BEFORE_RETURN);
}
