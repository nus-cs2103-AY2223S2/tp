package seedu.vms.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.util.AppUtil.checkArgument;

/**
 * Represents a Allergy in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAllergyName(String)}
 */
public class Allergy {

    public static final String MESSAGE_CONSTRAINTS = "Allergy names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String allergyName;

    /**
     * Constructs a {@code Allergy}.
     *
     * @param allergyName A valid allergy name.
     */
    public Allergy(String allergyName) {
        requireNonNull(allergyName);
        checkArgument(isValidAllergyName(allergyName), MESSAGE_CONSTRAINTS);
        this.allergyName = allergyName;
    }

    /**
     * Returns true if a given string is a valid allergy name.
     */
    public static boolean isValidAllergyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Allergy // instanceof handles nulls
                        && allergyName.equals(((Allergy) other).allergyName)); // state check
    }

    @Override
    public int hashCode() {
        return allergyName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '(' + allergyName + ')';
    }

}
