package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's diagnosis in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDiagnosis(String)}
 */
public class Diagnosis {

    public static final String MESSAGE_CONSTRAINTS =
            "Diagnosis should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String diagnosis;

    /**
     * Constructs a {@code Diagnosis}.
     *
     * @param diagnosis A valid diagnosis.
     */
    public Diagnosis(String diagnosis) {
        requireNonNull(diagnosis);
        checkArgument(isValidDiagnosis(diagnosis), MESSAGE_CONSTRAINTS);
        this.diagnosis = diagnosis;
    }

    /**
     * Returns true if a given string is a valid diagnosis.
     */
    public static boolean isValidDiagnosis(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return diagnosis;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Diagnosis // instanceof handles nulls
                && diagnosis.equals(((Diagnosis) other).diagnosis)); // state check
    }

    @Override
    public int hashCode() {
        return diagnosis.hashCode();
    }

}
