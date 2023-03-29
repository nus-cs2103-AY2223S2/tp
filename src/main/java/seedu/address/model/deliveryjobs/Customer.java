package seedu.address.model.deliveryjobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's customer in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCustomer(String)}
 * @deprecated merging with person
 */
@Deprecated
public class Customer {

    public static final String MESSAGE_CONSTRAINTS = "Customer can take any word, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\w";

    public final String value;

    /**
     * Constructs an {@code customer name}.
     *
     * @param customerName A valid customer name.
     */
    public Customer(String customerName) {
        requireNonNull(customerName);
        checkArgument(isValidCustomer(customerName), MESSAGE_CONSTRAINTS);
        value = customerName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCustomer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.deliveryjobs.Customer // instanceof handles nulls
                && value.equals(((seedu.address.model.deliveryjobs.Customer) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
