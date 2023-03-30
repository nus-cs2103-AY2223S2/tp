package seedu.connectus.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.util.AppUtil.checkArgument;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import seedu.connectus.model.person.Phone;

/**
 * Represents a Person's WhatsApp in ConnectUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidWhatsApp(String)}
 */
public class WhatsApp {

    public static final String MESSAGE_CONSTRAINTS = "WhatsApp's user identifier is a phone number. "
            + "Phone numbers should only contain numbers, and it should be at least 3 digits long\n"
            + "Format: " + PREFIX_SOCMED_WHATSAPP + "WHATSAPP";

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
