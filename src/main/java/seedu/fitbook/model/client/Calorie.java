package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's calorie intake in the FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidCalorie(String)} (String)}
 */
public class Calorie {

    public static final String MESSAGE_CONSTRAINTS =
            "Calories should only contain numbers, and it should be at least 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4,}";
    public final String value;

    /**
     * Constructs a {@code Calorie}.
     *
     * @param calorie A valid recommended calorie intake.
     */
    public Calorie(String calorie) {
        requireNonNull(calorie);
        checkArgument(isValidCalorie(calorie), MESSAGE_CONSTRAINTS);
        value = calorie;
    }

    /**
     * Returns true if a given string is a valid recommended calorie intake.
     */
    public static boolean isValidCalorie(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value.equals("0000")) {
            return "client has not added a calorie";
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calorie // instanceof handles nulls
                && value.equals(((Calorie) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
