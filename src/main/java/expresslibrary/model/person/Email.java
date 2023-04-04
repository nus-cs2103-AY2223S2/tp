package expresslibrary.model.person;

import static expresslibrary.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's email in the express library.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "The email address must consist of two parts separated by the @ symbol: a local part and a domain part.\n"
            + "The local part can contain letters, digits, and certain special characters, such as period, hyphen, and underscore.\n"
            + "However, it cannot begin or end with a period, and it cannot contain consecutive periods.\n"
            + "The local part cannot exceed 64 characters in length.\n"
            + "The domain part can contain letters, digits, hyphens, and periods.\n"
            + "However, it must contain at least one period and cannot begin or end with a hyphen or period.\n"
            + "Also, each label within the domain name must not exceed 63 characters in length, "
            + "and the overall length of the domain part cannot exceed 255 characters.\n"
            + "The domain name must conform to the DNS naming conventions, including restrictions on the characters "
            + "that can be used and the maximum length of each label.\n"
            + "The overall length of the email address cannot exceed 256 characters.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    public static final String VALIDATION_REGEX = "^(?=.{1,256}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@([a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$";
    public final String value;

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
