package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Company that a person belongs to in the address book.
 * Guarantees: is valid as declared in {@link #isValidCompany(String)} (String)}
 */
public class Company {


    public static final String MESSAGE_CONSTRAINTS =
            "Company cannot be blank and must be a valid String consisting of alphanumeric characters";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs an {@code BusinessSize}.
     *
     * @param companyName valid address.
     */
    public Company(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompany(companyName), MESSAGE_CONSTRAINTS);
        this.value = String.valueOf(companyName);
    }

    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && value.equals(((Company) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
