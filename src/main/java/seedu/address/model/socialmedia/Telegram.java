package seedu.address.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    private static final String SPECIAL_CHARACTERS = "_";
    public static final String MESSAGE_CONSTRAINTS = "Telegram username should of the format @johndoe or johndoe "
            + "and adhere to the following constraints:\n"
            + "1. The username should only contain alphanumeric characters and the special character, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + ").\n"
            + "2. The username should contain at least 5 characters.";
    // alphanumeric and special characters, beginning with 0 or 1 "@"
    private static final String USERNAME_REGEX = "@\\w+";

    // CHECKSTYLE.OFF: DeclarationOrder
    public static final Telegram DUMMY_TELEGRAM = new Telegram();
    // CHECKSTYLE.ON: DeclarationOrder

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegram A valid telegram username.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    private Telegram() {
        value = "";
    }

    /**
     * Returns if a given string is a valid telegram username.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(USERNAME_REGEX);
    }

    public static boolean isDummyTelegram(String test) {
        return test.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
