package seedu.patientist.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.AppUtil.checkArgument;

/**
 * API for an id number associated with a person.
 */
public class IdNumber {
    public static final String MESSAGE_CONSTRAINTS = "ID should be of the format: alphanumeric";

    /** ID should be alphanumeric. */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    protected final String idNumber;

    /**
     * Creates an idNumber.
     *
     * @param idNumber The idNumber in text form.
     */
    public IdNumber(String idNumber) {
        requireNonNull(idNumber);
        checkArgument(isValidPid(idNumber), MESSAGE_CONSTRAINTS);
        this.idNumber = idNumber.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid patient id.
     */
    public static boolean isValidPid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return idNumber;
    }

    @Override
    public int hashCode() {
        return idNumber.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IdNumber)) {
            return false;
        }
        return this.idNumber.equals(((IdNumber) other).idNumber);
    }
}
