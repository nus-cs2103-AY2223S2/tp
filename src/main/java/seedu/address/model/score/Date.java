package seedu.address.model.score;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Score's date in the Score object.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
        "Dates should only be in yyyy-MM-dd format, and it should not be blank";

    public static final String MESSAGE_INVALID_DATE =
        "The date entered is in the future. Please recheck the date entered.";

    // Identity field(s)
    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        checkArgument(isFutureDate(date), MESSAGE_INVALID_DATE);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if the date is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the date is not a future date.
     */
    public static boolean isFutureDate(String test) {
        LocalDate inputDate = LocalDate.parse(test);
        LocalDate computerDate = LocalDate.now();
        if (inputDate.isAfter(computerDate)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return date.toString();
    }

}
