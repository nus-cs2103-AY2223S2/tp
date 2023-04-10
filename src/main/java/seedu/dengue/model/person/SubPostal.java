package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's subpostal number in the Dengue Hotspot Tracker.
 * The subpostal number is a valid substring of a postal number.
 */
public class SubPostal {

    public static final String MESSAGE_CONSTRAINTS =
            "Substrings of Postal codes for find should only contain numbers or start with S or s"
                    + ", and be at least 1 digit long";
    public static final String VALIDATION_REGEX_FOR_SUB_POSTAL = "[Ss]?\\d{1,6}";
    public final String value;

    /**
     * Constructs a {@code SubPostal}.
     * @param subPostal A valid subpostal code.
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

    /**
     * Formats the subpostal such that it also contains the s.
     * @param string The subpostal to format.
     * @return The formatted subpostal.
     */
    public String formatSubPostal(String string) {
        boolean hasStart = string.substring(0, 1).equalsIgnoreCase("S");
        if (hasStart) {
            return string.toUpperCase();
        } else {
            return "S" + string;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubPostal // instanceof handles nulls
                && value.equals(((SubPostal) other).value)); // state check
    }
}
