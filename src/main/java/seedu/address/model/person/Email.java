package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String NULL_EMAIL = "NO_EMAIL";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only be e followed by 7 digits without any spaces \n"
            + "2. This is followed by a '@' and then a domain name. "
            + "The domain name must only be:\n"
            + "u.nus.edu\n";
    // alphanumeric and special characters
    private static final String NUMERIC_NO_UNDERSCORE = "e[0-9]{7}";
    private static final String LOCAL_PART_REGEX = "^" + NUMERIC_NO_UNDERSCORE;
    private static final String DOMAIN_REGEX = "u\\.nus\\.edu";
    //public static final String VALIDATION_REGEX = "^e[0-9]{7}@u.nus.edu";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    public Email() {
        value = NULL_EMAIL;
    }

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
