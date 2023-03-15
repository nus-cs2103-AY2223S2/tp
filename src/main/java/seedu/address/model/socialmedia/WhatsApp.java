package seedu.address.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Phone;

/**
 * Represents a Person's WhatsApp in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWhatsApp(String)}
 */
public class WhatsApp {

    public static final String MESSAGE_CONSTRAINTS = "WhatsApp user identifier is a phone number. "
            + Phone.MESSAGE_CONSTRAINTS;

    public final String value;

    private WhatsApp(String phone) {
        value = phone;
    }

    /**
     * Returns a {@code WhatsApp}.
     * @param phone A valid phone number.
     */
    public static WhatsApp of(String phone) {
        requireNonNull(phone);
        checkArgument(isValidWhatsApp(phone), MESSAGE_CONSTRAINTS);
        return new WhatsApp(phone);
    }

    /**
     * Returns if a given string is a valid telegram username.
     */
    public static boolean isValidWhatsApp(String test) {
        return Phone.isValidPhone(test);
    }

    public static WhatsApp fromPhone(Phone phone) {
        return WhatsApp.of(phone.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WhatsApp // instanceof handles nulls
                && value.equals(((WhatsApp) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
