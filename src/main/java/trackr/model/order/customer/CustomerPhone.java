package trackr.model.order.customer;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents the Customer's Phone Number
 * Guaruntees: immutable; is valid as declared in {@link #isValidCustomerPhone(String)}
 */
public class CustomerPhone {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be 8 digits long";
    public static final String VALIDATION_REGEX = "\\d{8}";
    public final String value;

    /**
     * Constructs a {@code CustomerPhone}.
     *
     * @param phone A valid customer phone number.
     */
    public CustomerPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidCustomerPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid customer phone number.
     */
    public static boolean isValidCustomerPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerPhone // instanceof handles nulls
                && value.equals(((CustomerPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
