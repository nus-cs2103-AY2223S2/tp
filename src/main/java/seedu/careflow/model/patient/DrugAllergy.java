package seedu.careflow.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's drug allergy in the patient record
 */
public class DrugAllergy {
    public static final String MESSAGE_CONSTRAINTS = "Drug allergy should be alphanumeric "
            + "and equal or less than 500 characters long";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Space}-]{0,499}+";
    public final String value;

    /**
     * Constructs a {@code DrugAllergy}.
     *
     * @param drugAllergy A valid drug allergy description.
     */
    public DrugAllergy(String drugAllergy) {
        requireNonNull(drugAllergy);
        checkArgument(isValidDrugAllergy(drugAllergy), MESSAGE_CONSTRAINTS);
        this.value = drugAllergy;
    }

    /**
     * Returns true if a given string is a valid drug allergy.
     */
    public static boolean isValidDrugAllergy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DrugAllergy // instanceof handles nulls
                && value.equals(((DrugAllergy) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
