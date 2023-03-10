package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWebsite(String)}
 */
public class Website {

    public static final String MESSAGE_CONSTRAINTS = "Websites should be of the format www.domain.com"
            + " and adhere to the following constraints:\n"
            + "1. The first part should only contain www.\n"
            + "2. This is followed by a '.' and the website/domain name.\n"
            + "3. The last part should end with '.com'.";
    // alphanumeric and special character

    /* private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "." + DOMAIN_REGEX;
     */
    public static final String VALIDATION_REGEX = "[w]{3}\\.[\\w]+\\.[c][o][m]";

    public final String value;

    /**
     * Constructs an {@code Website}.
     *
     * @param webAddress A valid website address.
     */
    public Website(String webAddress) {
        requireNonNull(webAddress);
        checkArgument(isValidWebsite(webAddress), MESSAGE_CONSTRAINTS);
        this.value = webAddress;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidWebsite(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Website // instanceof handles nulls
                && value.equals(((Website) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
