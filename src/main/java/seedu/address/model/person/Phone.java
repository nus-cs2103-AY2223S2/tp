package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String NULL_PHONE = "NO_PHONE";
    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle must be at least 5-characters long and maximum 32-character long, "
            + "and may consist only of a-z, A-Z, 0–9, and underscores. "
            + "No whitespaces allowed between characters";
    //public static final String VALIDATION_REGEX = "\\d{3,}";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]{5,32}$";

    public final String value;

    public Phone() {
        value = NULL_PHONE;
    }

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        if (test.length() != test.trim().length()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
