package seedu.address.model.person;

/**
 * Represents the Company that a person belongs to in the address book.
 * Guarantees: is valid as declared in {@link #isValidCompany(String)} (String)}
 */
public class Company {


    public static final String MESSAGE_CONSTRAINTS =
            "Company cannot be blank and must be a valid String";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*"; //check if this actually works
    public final String value;

    /**
     * Constructs an {@code BusinessSize}.
     *
     * @param companyName valid address.
     */
    public Company(String companyName) {
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
