package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's Company in the internship catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Names of company should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String companyName;

    /**
     * Constructs a {@code Company}.
     *
     * @param companyName A valid name for a Company.
     */
    public Company(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompany(companyName), MESSAGE_CONSTRAINTS);
        this.companyName = companyName;
    }

    /**
     * Returns true if a given string is a valid Company.
     */
    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return companyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && companyName.equals(((Company) other).companyName)); // state check
    }

    @Override
    public int hashCode() {
        return companyName.hashCode();
    }

}
