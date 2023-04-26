package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's weight in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS =
            "Weight should be in kilograms (kg), can have at most 1 decimal place, and not equal to 0.";

    private static final String VALIDATION_REGEX = "^\\s*\\d+(\\.\\d)?\\s*$";

    private final String value;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid weight in kilograms (kg).
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        String trimmedWeight = weight.trim();
        this.value = trimmedWeight;
    }

    /**
     * Returns true if a given string is a valid weight.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX) && !test.equals("0") && !test.equals("0.0");
    }

    @Override
    public String toString() {
        return value + " kg";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }
}
