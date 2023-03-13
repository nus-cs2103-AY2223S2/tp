package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's company in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Companies should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the company must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String companyName;

    /**
     * Constructs a {@code Company}.
     *
     * @param companyName A valid company name.
     */
    public Company(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompanyName(companyName), MESSAGE_CONSTRAINTS);
        this.companyName = companyName;
    }

    /**
     * Returns true if a given string is a valid company name.
     */
    public static boolean isValidCompanyName(String test) {
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
