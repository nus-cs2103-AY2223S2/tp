package seedu.connectus.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.util.AppUtil.checkArgument;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;

/**
 * Represents a Person's WhatsApp in ConnectUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidWhatsApp(String)}
 */
public class WhatsApp implements Openable, Chatable {

    public static final String MESSAGE_CONSTRAINTS = "WhatsApp's user identifier is a phone number.\n"
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
    public String getUserLink() {
        // Singaporean phone number? add +65 prefix.
        return "whatsapp://send?phone=" + ((value.length() == 8) ? "65" + value : value);
    }

    public static String getUserLink(Person user) {
        return user.getSocialMedia().map(SocialMedia::getWhatsapp).map(WhatsApp::getUserLink).orElse("");
    }

    @Override
    public String getUserLinkWithPreparedMessage(String message) {
        return getUserLink() + "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
    }

    public static String getUserLinkWithPreparedMessage(Person user, String message) {
        return user.getSocialMedia().map(SocialMedia::getWhatsapp)
            .map(u -> u.getUserLinkWithPreparedMessage(message)).orElse("");
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
