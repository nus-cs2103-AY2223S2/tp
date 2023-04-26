package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's Nric in MedInfo.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS = "NRIC should not be blank. It should only begin with either "
        + "the letter S, T, F or G, followed by 7 numbers, then ending with a capital letter.";

    /**
     * The National Registry Identification Number (NRIC) of Singapore
     * is made up of the first character being a S/F/T or G.
     * The next 2 numbers is the year of birth for people born 1967 and later.
     * The last character is a checksum done on the numbers,
     * and the algorithm will not be released.
     */
    public static final String VALIDATION_REGEX = "^[STFG]\\d{7}[A-Z]$";

    public final String value;

    /**
     * Constructs an {@code Nric}.
     *
     * @param nric A valid NRIC.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric;
    }

    /**
     * Returns true if a given string is a valid NRIC.
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
                        && value.equals(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
