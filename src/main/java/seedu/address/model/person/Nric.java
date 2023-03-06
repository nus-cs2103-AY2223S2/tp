package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should be 9 characters, and it should not be blank. \n"
            + "The first character must be one of [S,T,F,G,M], followed by 7 numerical digits and ending with [A-Z].";

    /*
     * The first character of the NRIC must be 9 digits long.
     * Starting character must be one of [S,T,F,G,M] and ending with [A-Z].
     * In between there must be 7 digits [0-9].
     */
    public static final String VALIDATION_REGEX = "[S,T,F,G,M]{1}[0-9]{7}[A-Z]{1}";

    public final String nric;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid Singaporean NRIC number.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        this.nric = nric;
    }

    /**
     * Returns true if a given string is a valid NRIC.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && nric.equals(((Nric) other).nric)); // state check
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }

}
