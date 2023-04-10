package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's drug alllergy in the patient records.
 * Guarantees: immutable; is valid as declared in {@link #isValidDrugAllergy(String)}
 */
public class DrugAllergy {
    public static final String MESSAGE_CONSTRAINTS = "Drug allergies can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code DrugAllergy}.
     *
     * @param drugAllergy A valid drug allergy.
     */
    public DrugAllergy(String drugAllergy) {
        requireNonNull(drugAllergy);
        checkArgument(isValidDrugAllergy(drugAllergy), MESSAGE_CONSTRAINTS);
        value = drugAllergy;
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
