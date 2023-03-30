package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's ProgrammingLanguage in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidProgrammingLanguage(String)}
 */
public class ProgrammingLanguage extends InternshipApplicationAttribute {

    public static final String MESSAGE_CONSTRAINTS = "Programming Languages can take any values, "
        + "and it should not be blank";

    /*
     * The first character of the ProgrammingLanguage must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ProgrammingLanguage}.
     *
     * @param x A valid ProgrammingLanguage.
     */
    public ProgrammingLanguage(String x) {
        requireNonNull(x);
        checkArgument(isValidProgrammingLanguage(x), MESSAGE_CONSTRAINTS);
        value = x;
    }

    /**
     * Returns true if a given string is a valid ProgrammingLanguage.
     */
    public static boolean isValidProgrammingLanguage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProgrammingLanguage// instanceof handles nulls
                && value.equals(((ProgrammingLanguage) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

