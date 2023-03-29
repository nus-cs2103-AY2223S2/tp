package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's qualification in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidQualification(String)}
 */
public class Qualification {

    public static final String MESSAGE_CONSTRAINTS = "Qualifications can take any values, and it should not be blank";

    /*
     * The first character of the qualification must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Qualification}.
     *
     * @param x A valid qualification.
     */
    public Qualification(String x) {
        requireNonNull(x);
        checkArgument(isValidQualification(x), MESSAGE_CONSTRAINTS);
        value = x;
    }

    /**
     * Returns true if a given string is a valid qualification.
     */
    public static boolean isValidQualification(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Qualification// instanceof handles nulls
                && value.equals(((Qualification) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

