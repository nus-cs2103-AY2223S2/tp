package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's postal number in the Dengue Hotspot Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostal(String)}
 */
public class Postal {

    public static final String MESSAGE_CONSTRAINTS =
            "Postal codes should only contain numbers or start with S, and it must be 6 digits long,\n"
                    + " beginning with a valid two-digit postal sector.";
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
        if (test.length() < 6) {
            return false;
        }
        String postalSector = extractPostalSector(test);
        try { // check valid postal sector
            PostalSector.valueOf("SECTOR" + postalSector);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    private static String extractPostalSector(String postal) {
        return postal.toUpperCase().startsWith("S") ? postal.substring(1, 3) : postal.substring(0, 2);
    }

    /**
     * Returns the {@code PostalSector} corresponding to the postal code.
     *
     * @return The corresponding {@code PostalSector}.
     */
    public PostalSector getPostalSector() {
        assert isValidPostal(value);

        String postalSector = extractPostalSector(value);
        return PostalSector.valueOf("SECTOR" + postalSector);
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
