package seedu.sprint.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.commons.util.AppUtil.checkArgument;

/**
 * Represents the company's email for an Application in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}.
 */
public class CompanyEmail {

    public static final String MESSAGE_CONSTRAINTS = "Emails should follow the format: local-part@domain.\n"
            + "Example: google_careers@gmail.com";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs a {@code CompanyEmail}.
     *
     * @param email A valid email sprint.
     */
    public CompanyEmail(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns true if a given string is a valid email.
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
                || (other instanceof CompanyEmail // instanceof handles nulls
                && value.equals(((CompanyEmail) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
