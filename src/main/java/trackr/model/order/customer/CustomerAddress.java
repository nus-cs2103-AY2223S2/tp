package trackr.model.order.customer;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Customer's Address
 * Guaruntees: immutable; is valid as declared in {@link #isValidCustomerAddress(String)}
 */
public class CustomerAddress {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code CustomerAddress}.
     *
     * @param address A valid customer address.
     */
    public CustomerAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidCustomerAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid customer address.
     */
    public static boolean isValidCustomerAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerAddress // instanceof handles nulls
                && value.equals(((CustomerAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
