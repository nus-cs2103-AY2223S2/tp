package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's current medication
 * Guarantees: immutable; is valid as declared in {@link #isValidMedication(String)}
 */
public class Medication {

    public static final String MESSAGE_CONSTRAINTS = "Medication can take any values, and it should not be blank";

    /*
     * Any string will do.
     */
    public static final String VALIDATION_REGEX = "(.*?)";

    public final String value;

    /**
     * Constructs an {@code Medication}.
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

    /**
     * Returns true if Medication value is an empty string.
     */
    public boolean isEmpty() {
        return value.length() == 0;
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
