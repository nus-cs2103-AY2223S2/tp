package teambuilder.model.person;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*|^\\s*$";

    private static final Address EMPTY_ADDRESS = new Address();
    private final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    private Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    private Address() {
        value = "";
    }

    /**
     * Public method to create addresses.
     * Note that empty string or string containing only white spaces will return the default EMPTY_ADDRESS instance.
     *
     * @param address   The address input.
     * @return          An Address instance with the given input
     */
    public static Address of(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        if (address.length() == 0) {
            return getEmptyAddress();
        }
        return new Address(address);
    }

    public static Address getEmptyAddress() {
        return EMPTY_ADDRESS;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isEmptyAddress() {
        return this == EMPTY_ADDRESS;
    }
}
