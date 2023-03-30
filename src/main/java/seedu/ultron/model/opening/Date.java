package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Opening's date in the address book.
 */
public class Date implements Comparable<Date> {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only be in the format yyyy-MM-dd e.g. 2023-01-01";

    public final String fullDate;
    public final String fullName;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String name, String date) {
        requireNonNull(date);
        requireNonNull(name);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        fullDate = date;
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(test, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the date is in the past.
     */
    public boolean isPastDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(fullDate, dateFormat);
            if (date.isBefore(LocalDate.now())) {
                return true;
            }
            return false;
        } catch (DateTimeParseException e) {
            // Should not happen
            return false;
        }
    }

    @Override
    public String toString() {
        return fullName + ": " + fullDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && fullName.equals(((Date) other).fullName)
                && fullDate.equals(((Date) other).fullDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, fullDate);
    }

    @Override
    public int compareTo(Date other) {
        if (other == null) {
            return 1;
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate thisDate = LocalDate.parse(fullDate, dateFormat);
            LocalDate otherDate = LocalDate.parse(other.fullDate, dateFormat);
            return thisDate.compareTo(otherDate);
        }
    }
}
