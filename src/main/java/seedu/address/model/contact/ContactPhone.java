package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's phone number in the contact book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class ContactPhone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be 8 digits long";
    public static final String VALIDATION_REGEX = "\\d{8}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public ContactPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactPhone // instanceof handles nulls
                && value.equals(((ContactPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
