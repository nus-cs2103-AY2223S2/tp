package seedu.sudohr.model.leave;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Leave's date in the sudohr book.
 */
public class LeaveDate implements Comparable<LeaveDate> {

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A LocalDate object representing the leave date.
     */
    public LeaveDate(LocalDate date) {
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
                || (other instanceof LeaveDate // instanceof handles nulls
                        && value.equals(((LeaveDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(LeaveDate o) {
        return this.value.compareTo(o.value);
    }

}
