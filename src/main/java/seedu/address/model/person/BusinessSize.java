package seedu.address.model.person;

/**
 * Represents a Person's business size in the address book.
 * Guarantees: is valid as declared in {@link #isValidBusinessSize(String)} (String)}
 */
public class BusinessSize {


    public static final String MESSAGE_CONSTRAINTS =
            "Business Size can take any number greater than or equal to 0, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\\\s].*"; //todo: change to take in number >= 0
    public final String value;

    /**
     * Constructs an {@code BusinessSize}.
     *
     * @param businessSize A valid address.
     */
    public BusinessSize(String businessSize) {
        this.value = String.valueOf(businessSize);
    }

    public static boolean isValidBusinessSize(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BusinessSize // instanceof handles nulls
                && value.equals(((BusinessSize) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
