package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a role's job description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobDescription(String)}
 */
public class Experience {

    public static final String MESSAGE_CONSTRAINTS = "Experience can take any values, and it should not be blank";

    /*
     * The first character of the job description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code experience}.
     *
     * @param x A valid experience.
     */
    public Experience(String x) {
        requireNonNull(x);
        checkArgument(isValidExperience(x), MESSAGE_CONSTRAINTS);
        value = x;
    }

    /**
     * Returns true if a given string is a valid experience.
     */
    public static boolean isValidExperience(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the message constraint of experience.
     *
     * @return messageConstraint returns the message constraint of the given experience.
     */
    public static String getMessageConstraint() {
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Experience // instanceof handles nulls
                && value.equals(((Experience) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
