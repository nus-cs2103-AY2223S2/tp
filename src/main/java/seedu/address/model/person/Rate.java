package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Rate {

    public static final String MESSAGE_CONSTRAINTS =
            "Rate should be a positive value, it should be more than 0.";
    public final double value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Rate(String value) {
        requireNonNull(value);
        checkArgument(isValidRate(value.toString()), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if a given string is a valid rate.
     */
    public static boolean isValidRate(String test) {
        try {
            return Double.parseDouble(test) > 0; //test.matches("[-+]?[0-9]*\\.?[0-9]+")
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rate // instanceof handles nulls
                && value == ((Rate) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
