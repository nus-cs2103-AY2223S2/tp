package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternshipApplication's companyName in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class CompanyName extends InternshipApplicationAttribute implements Comparable<CompanyName> {

    public static final String MESSAGE_CONSTRAINTS =
            "Company Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param companyName A valid companyName.
     */
    public CompanyName(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompanyName(companyName), MESSAGE_CONSTRAINTS);
        fullName = companyName;
    }

    /**
     * Returns true if a given string is a valid companyName.
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(CompanyName companyName) {
        return fullName.toUpperCase().compareTo(companyName.fullName.toUpperCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && fullName.equals(((CompanyName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
