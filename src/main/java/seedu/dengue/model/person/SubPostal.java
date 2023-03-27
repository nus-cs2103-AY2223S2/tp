package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

public class SubPostal {

    public static final String MESSAGE_CONSTRAINTS =
            "Postal codes for find should only contain numbers or start with S or s, and have at least 1 digit long";
    public static final String VALIDATION_REGEX_FOR_SUB_POSTAL = "[Ss]?\\d{1,6}";
    public final String value;

    /**
     * Constructs a {@code Postal}.
     * @param postal A valid postal code.
     */
    public SubPostal(String subPostal) {
        requireNonNull(subPostal);
        checkArgument(isValidSubPostal(subPostal), MESSAGE_CONSTRAINTS);
        value = formatSubPostal(subPostal);
    }

    /**
     * Returns true if a given string is a valid substring of a postal code.
     */
    public static boolean isValidSubPostal(String test) {
        return test.matches(VALIDATION_REGEX_FOR_SUB_POSTAL);
    }

    public String formatSubPostal(String string) {
        boolean hasStart = string.substring(0, 1).toUpperCase().equals("S");
        if (hasStart) {
            return string.toUpperCase();
        } else {
            return "S" + string;
        }
    }
}
