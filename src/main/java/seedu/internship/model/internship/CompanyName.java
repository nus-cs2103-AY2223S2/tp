package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's company name in InternBuddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class CompanyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Company names should not be blank, and should be at most 50 characters";

    /*
     * The first character of the company name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^.{1,50}$";

    public final String fullCompanyName;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param companyName A valid company name.
     */
    public CompanyName(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompanyName(companyName), MESSAGE_CONSTRAINTS);
        fullCompanyName = companyName;
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test The regex to test for a valid name.
     * @return true if the given input String is a valid company name
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns the String representation of the company name.
     *
     * @return a string corresponding to the company name.
     */
    @Override
    public String toString() {
        return fullCompanyName;
    }

    /**
     * Determines if an object is equal to the current Internship object.
     *
     * @param other The other object to be compared with.
     * @return true if the object is equal to the current Internship entry, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && fullCompanyName.toLowerCase().equals((
                        (CompanyName) other).fullCompanyName.toLowerCase())); // state check
    }

    /**
     * Generates the hashcode for the company's name.
     *
     * @return the hash code representing the company name.
     */
    @Override
    public int hashCode() {
        return fullCompanyName.hashCode();
    }

}
