package seedu.address.model.socialmedia;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's instagram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Instagram {

    private static final String SPECIAL_CHARACTERS = ".";
    public static final String MESSAGE_CONSTRAINTS = "Instagram username should of the format john.123.doe "
            + "and adhere to the following constraints:\n"
            + "1. The username should only contain alphanumeric characters and the special character, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + ").\n"
            + "2. The dots must not be consecutive or at the end.\n"
            + "3. The username should contain at most 30 characters.";

    // alphanumeric and dot; no consecutive dots, not ending with dot
    private static final String USERNAME_REGEX = "([a-zA-Z0-9]\\.?)*[a-zA-Z0-9]";

    public final String value;

    private Instagram(String telegram) {
        value = telegram;
    }

    /**
     * Returns an {@code Instagram}.
     * @param telegram A valid instagram username.
     */
    public static Instagram of(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValid(telegram), MESSAGE_CONSTRAINTS);
        return new Instagram(telegram);
    }

    /**
     * Returns if a given string is a valid instagram username.
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
                || (other instanceof Instagram // instanceof handles nulls
                && value.equals(((Instagram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
