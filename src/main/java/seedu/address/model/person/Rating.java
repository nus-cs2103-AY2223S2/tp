package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's Rating in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating extends InternshipApplicationAttribute {

    public static final String MESSAGE_CONSTRAINTS = "Ratings can take any values";
    public static final String VALIDATION_REGEX = ".*";

    public final String value;

    /**
     * Constructs an {@code Rating}.
     *
     * @param x A valid Rating.
     */
    public Rating(String x) {
        checkArgument(isValidRating(x), MESSAGE_CONSTRAINTS);
        value = x;
    }

    /**
     * Returns true if a given string is a valid Rating.
     */
    public static boolean isValidRating(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating// instanceof handles nulls
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

