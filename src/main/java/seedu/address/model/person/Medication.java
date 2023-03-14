package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's current medication
 * Guarantees: immutable; is valid as declared in {@link #isValidMedication(String)}
 */
public class Medication {

    private static final String DELIMITER = ";";
    public static final String MESSAGE_CONSTRAINTS = "Medication should be of the format qty medication. "
            + "If you would like to prescribe multiple medications, insert a " + DELIMITER + "between each string.";

    /*
     * Accepts one of the following 2 cases:
     * An empty string
     * number medication[;number medication]*
     */
    // ^$|\\d+ [^+;+]+(;\\s*\\d+ [^;]+)*
    public static final String VALIDATION_REGEX = "^$|\\d+ [^+"  + DELIMITER + "+]+(" + DELIMITER
            + "\\s*\\d+ [^" + DELIMITER +"]+)*";

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
