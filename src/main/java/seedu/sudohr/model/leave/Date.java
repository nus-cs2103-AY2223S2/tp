package seedu.sudohr.model.leave;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Leave's date in the sudohr book.
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Titles should only contain alphanumeric characters"
            + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final LocalDate value;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A leave date.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        value = date;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                        && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
