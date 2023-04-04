package seedu.address.model.person;

/**
 * Represents a Person's business size in the address book.
 * Guarantees: is valid as declared in {@link #isValidBusinessSize(String)} (String)}
 */
public class BusinessSize implements Comparable<BusinessSize> {


    public static final String MESSAGE_CONSTRAINTS =
            "Potential Sale Value can take any number greater than or equal to 0, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[0-9]\\d*$";

    public final String value;

    /**
     * Constructs an {@code BusinessSize}.
     *
     * @param businessSize A valid address.
     */
    public BusinessSize(String businessSize) {
        this.value = businessSize.strip();
    }

    public long getNumericValue() {
        return Long.parseLong(this.value);
    }

    public static boolean isValidBusinessSize(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     * Returns integer representation of business size value
     * @return Integer
     */
    public Long getLongValue() {
        try {
            return Long.valueOf(this.value);
        } catch (Exception e) {
            return Long.valueOf(0);
        }
    }

    @Override
    public int compareTo(BusinessSize businessSize) {
        if (this.getLongValue() - businessSize.getLongValue() > 0) {
            return 1;
        } else if (this.getLongValue() - businessSize.getLongValue() < 0) {
            return -1;
        } else {
            return 0;
        }
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
