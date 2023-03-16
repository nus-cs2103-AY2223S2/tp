package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;

/**
 * Represents a Person's NRIC in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should only be in the format " + "@xxxxxxx#, where:\n"
                    + "@ is a letter that can be \"S\", \"T\", \"F\", \"G\" or \"M\" \n"
                    + "xxxxxxx is a 7-digit serial number, each x can be any number 0-9\n"
                    + "# can be any captial alphabet A-Z, and the field cannot be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[STFGM]\\d{7}[A-Z]$";

    public final String fullNric;

    /**
     * Constructs a {@code NRIC}.
     *
     * @param nric A valid NRIC.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        fullNric = nric;
    }

    /**
     * Returns true if a given string is a valid NRIC.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullNric;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && fullNric.equals(((Nric) other).fullNric)); // state check
    }

    @Override
    public int hashCode() {
        return fullNric.hashCode();
    }

}
