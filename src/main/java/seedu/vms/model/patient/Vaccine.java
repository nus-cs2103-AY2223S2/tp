package seedu.vms.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.util.AppUtil.checkArgument;

/**
 * Represents a Vaccine in the patient manager.
 * Guarantees: immutable; name is valid as declared in {@link #isValidVaccineName(String)}
 */
public class Vaccine {

    public static final String MESSAGE_CONSTRAINTS = "Vaccine names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String vaccineName;

    /**
     * Constructs a {@code Vaccine}.
     *
     * @param vaccineName A valid allergy name.
     */
    public Vaccine(String vaccineName) {
        requireNonNull(vaccineName);
        checkArgument(isValidVaccineName(vaccineName), MESSAGE_CONSTRAINTS);
        this.vaccineName = vaccineName;
    }

    /**
     * Returns true if a given string is a valid allergy name.
     */
    public static boolean isValidVaccineName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Vaccine // instanceof handles nulls
                        && vaccineName.equals(((Vaccine) other).vaccineName)); // state check
    }

    @Override
    public int hashCode() {
        return vaccineName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '(' + vaccineName + ')';
    }

}
