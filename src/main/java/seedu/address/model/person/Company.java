package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Company in the openings list.
 * Guarantees: immutable;
 */
public class Company {

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param company A valid company.
     */
    public Company(String company) {
        requireNonNull(company);
        value = company;
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
