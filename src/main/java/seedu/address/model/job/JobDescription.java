package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a role's job description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobDescription(String)}
 */
public class JobDescription {

    public static final String MESSAGE_CONSTRAINTS = "Job descriptions can take any values, and it should not be blank";

    /*
     * The first character of the job description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code JobDescription}.
     *
     * @param jd A valid Job Description.
     */
    public JobDescription(String jd) {
        requireNonNull(jd);
        checkArgument(isValidJobDescription(jd), MESSAGE_CONSTRAINTS);
        value = jd;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidJobDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobDescription // instanceof handles nulls
                && value.equals(((JobDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
