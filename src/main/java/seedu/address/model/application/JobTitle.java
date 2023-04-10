package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternshipApplication's jobTitle in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle extends InternshipApplicationAttribute implements Comparable<JobTitle> {

    public static final String MESSAGE_CONSTRAINTS =
            "Job titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code JobTitle}.
     *
     * @param jobTitle A valid jobTitle.
     */
    public JobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle), MESSAGE_CONSTRAINTS);
        fullName = jobTitle;
    }

    /**
     * Returns true if a given string is a valid jobTitle.
     */
    public static boolean isValidJobTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitle // instanceof handles nulls
                && fullName.equals(((JobTitle) other).fullName)); // state check
    }

    @Override
    public int compareTo(JobTitle jobTitle) {
        return this.fullName.toUpperCase().compareTo(jobTitle.fullName.toUpperCase());
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
