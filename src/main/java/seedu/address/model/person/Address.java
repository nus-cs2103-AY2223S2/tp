package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.location.Location;
import seedu.address.model.location.LocationUtil;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address implements Comparable<Address> {

    public static final String MESSAGE_CONSTRAINTS =
            "Addresses should be the "
            + "names of the closest MRT stations to their homes.";

    private final Location value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = LocationUtil.ADDRESSES_HASH_MAP.get(address.toLowerCase());
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test != null
                && LocationUtil.ADDRESSES_HASH_MAP.containsKey(test.trim().toLowerCase());
    }

    /**
     * Gets the String value stored within the email.
     */
    public Location getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue().getName();
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

    @Override
    public int compareTo(Address otherAddress) {
        return value.compareTo(otherAddress.value);
    }

}
