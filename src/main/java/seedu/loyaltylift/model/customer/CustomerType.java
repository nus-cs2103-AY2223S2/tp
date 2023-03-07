package seedu.loyaltylift.model.customer;

public enum CustomerType {
    INDIVIDUAL,
    ENTERPRISE;

    public static final String MESSAGE_FAIL_CONVERSION = "NO SUCH CUSTOMER TYPE";

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
