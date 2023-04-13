package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's medication
 * Guarantees: immutable; is valid as declared in {@link #isValidMedication(String)}
 */
public class Medication {

    public static final String MESSAGE_CONSTRAINTS = "Medications should only contain alphanumeric character and"
            + "spaces, and it should not be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Medication}.
     *
     * @param medication A valid medication.
     */
    public Medication(String medication) {
        requireNonNull(medication);
        checkArgument(isValidMedication(medication), MESSAGE_CONSTRAINTS);
        value = medication;
    }

    /**
     * Returns true if a given string is a valid medication.
     */
    public static boolean isValidMedication(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && value.equals(((Medication) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
