package seedu.address.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Telegram {

    private static final String SPECIAL_CHARACTERS = "_";
    public static final String MESSAGE_CONSTRAINTS = "Telegram username should of the format johndoe "
            + "and adhere to the following constraints:\n"
            + "1. The username should only contain alphanumeric characters and the special character, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + ").\n"
            + "2. The username should contain at least 5 characters.";
    // alphanumeric and special characters, beginning with 0 or 1 "@"
    private static final String USERNAME_REGEX = "\\w+";

    public final String value;

    private Telegram(String telegram) {
        value = telegram;
    }

    /**
     * Returns a {@code Telegram}.
     * @param telegram A valid telegram username.
     */
    public static Telegram of(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValid(telegram), MESSAGE_CONSTRAINTS);
        return new Telegram(telegram);
    }

    /**
     * Returns if a given string is a valid telegram username.
     */
    public static boolean isValid(String test) {
        return test.matches(USERNAME_REGEX);
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
