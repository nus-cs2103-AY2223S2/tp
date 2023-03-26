package bookopedia.model;

/**
 * Parcel status.
 */
public enum ParcelStatus {
    FRAGILE, BULKY;

    public static final String MESSAGE_CONSTRAINTS = "Parcel status must be of the following: "
            + "fragile, bulky";
}
