package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;

/**
 * Represents an Internship's date in InternBuddy. The date is associated with the status of the Internship.
 * For example, if the Internship's status is ASSESSMENT, then the date will be inferred as the date of assessment.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format yyyy-MM-dd, " +
            "and it should be a valid date";

    public final String fullDate;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date;
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        fullDate = date;
    }

    /**
     * Returns if a given string is a valid date.
     *
     * @returns true if a given string is a valid date string, else returns false.
     */
    public static boolean isValidDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(test);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Returns a String representation for the Date.
     *
     * @returns a string representing the date.
     */
    @Override
    public String toString() {
        return fullDate;
    }

    /**
     * Determines if another object is equal to the current {@code Date} object.
     *
     * @param other The other object to compare with.
     * @return true if the other object is a {@code Date} object with the same date string.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && fullDate.equals(((Date) other).fullDate)); // state check
    }

    /**
     * Gets the hashcode for the date represented by this {@code Date} object.
     *
     * @return the hashcode for this {@code Date} object.
     */
    @Override
    public int hashCode() {
        return fullDate.hashCode();
    }

}
