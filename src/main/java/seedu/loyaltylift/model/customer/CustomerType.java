package seedu.loyaltylift.model.customer;

import static java.util.Objects.requireNonNull;

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
    public static CustomerType fromUserFriendlyString(String customerType) {
        requireNonNull(customerType);

        String uCustomerType = customerType.toUpperCase();
        switch (uCustomerType) {
        case "IND":
            return CustomerType.INDIVIDUAL;
        case "ENT":
            return CustomerType.ENTERPRISE;
        default:
            return valueOf(uCustomerType);
        }
    }
}
