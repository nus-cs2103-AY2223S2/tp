package seedu.loyaltylift.model.customer;

/**
 * Represents a Customer's type in the address book.
 * Currently, it is either an individual or an enterprise.
 */
public enum CustomerType {
    INDIVIDUAL,
    ENTERPRISE;

    public static final String MESSAGE_FAIL_CONVERSION = "Unrecognised customer type";

    /**
     * Returns CustomerType based on the given {@code customerType}.
     * This is to allow users to type short forms of the enum instead of the full string.
     * Both upper and lower cases are accepted.
     * @param customerType The short form or full string of a CustomerType.
     * @return A CustomerType representing the string given.
     * @throws IllegalArgumentException Thrown when the string given is unrecognised.
     */
    public static CustomerType fromUserFriendlyString(String customerType) throws IllegalArgumentException {
        if (customerType == null) {
            throw new IllegalArgumentException();
        }

        switch (customerType.toLowerCase()) {
        case "ind":
            return CustomerType.INDIVIDUAL;
        case "ent":
            return CustomerType.ENTERPRISE;
        default:
            return valueOf(customerType.toUpperCase());
        }
    }
}
