package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's postal number in the Dengue Hotspot Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostal(String)}
 */
public class Postal {


    public static final String MESSAGE_CONSTRAINTS =
            "Postal codes should only contain numbers or start with S, and it must be 6 digits long";
    public static final String VALIDATION_REGEX = "[Ss]?\\d{6}";
    public final String value;

    /**
     * Constructs a {@code Postal}.
     * @param postal A valid postal code.
     */
    public Postal(String postal) {
        requireNonNull(postal);
        checkArgument(isValidPostal(postal), MESSAGE_CONSTRAINTS);
        value = formatPostal(postal);
    }


    /**
     * Formats a postal code to be in the form "^S\\d{6}$"
     * @param string Postal code to format.
     * @return A formatted postal code.
     */
    public String formatPostal(String string) {
        boolean hasStart = string.substring(0, 1).toUpperCase().equals("S");
        if (hasStart) {
            return string.toUpperCase();
        } else {
            return "S" + string;
        }
    }

    /**
     * Returns true if a given string is a valid postal code.
     */
    public static boolean isValidPostal(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Postal // instanceof handles nulls
                && value.equals(((Postal) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
