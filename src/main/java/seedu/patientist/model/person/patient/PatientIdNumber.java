package seedu.patientist.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.AppUtil.checkArgument;

/**
 * Object representing the ID number of a patient. ID numbers are automatically capitalised when they are created.
 */
public class PatientIdNumber {
    public static final String MESSAGE_CONSTRAINTS = "PID should be alnum.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    private final String idNumber;

    /**
     * Constructor for PatientIdNumber.
     * @param idNumber String to be converted.
     */
    //TODO: include error checking for invalid ID numbers
    public PatientIdNumber(String idNumber) {
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

    public String getIdNumber() {
        return idNumber;
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
        if (!(other instanceof PatientIdNumber)) {
            return false;
        }
        return this.idNumber.equals(((PatientIdNumber) other).idNumber);
    }
}
