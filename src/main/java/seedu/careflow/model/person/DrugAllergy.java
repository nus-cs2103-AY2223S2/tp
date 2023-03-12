package seedu.careflow.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

import seedu.careflow.model.tag.Tag;

/**
 * Represents a patient's drug allergy in the patient record
 */
public class DrugAllergy {
    public static final String MESSAGE_CONSTRAINTS = "Drug allergy should be alphanumeric "
            + "and less than 500 characters long";
    public static final String VALIDATION_REGEX = "\\p{Alnum}{1,499}+";
    private final Tag drugAllergy;

    /**
     * Constructs a {@code DrugAllergy}.
     *
     * @param drugAllergy A valid drug allergy description.
     */
    public DrugAllergy(String drugAllergy) {
        requireNonNull(drugAllergy);
        checkArgument(isValidDrugAllergy(drugAllergy), MESSAGE_CONSTRAINTS);
        this.drugAllergy = new Tag(drugAllergy);
    }

    /**
     * Returns true if a given string is a valid drug allergy.
     */
    public static boolean isValidDrugAllergy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return drugAllergy.tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DrugAllergy // instanceof handles nulls
                && drugAllergy.equals(((DrugAllergy) other).drugAllergy)); // state check
    }

    @Override
    public int hashCode() {
        return drugAllergy.hashCode();
    }
}
