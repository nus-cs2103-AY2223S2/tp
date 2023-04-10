package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in FriendlyLink.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS = "Invalid arguments. \n"
            + "The structure of the NRIC should be @xxxxxxx#, where:\n"
            + "1. @ is a letter that can be \"S\", \"T\", \"F\", \"G\" or \"M\"\n"
            + "2. xxxxxxx is a 7-digit serial number\n"
            + "3. # is a letter from A-Z";
    public static final String VALIDATION_REGEX = "^[STFGMstfgm]\\d{7}[A-Za-z]$";
    public final String value;

    /**
     * Constructs an {@code Nric}.
     *
     * @param nric A valid NRIC.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid NRIC.
     *
     * @param test Nric to be tested.
     * @return True if {@code test} is a valid NRIC and false otherwise.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && value.equalsIgnoreCase(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
