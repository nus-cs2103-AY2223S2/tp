package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents an Opening's date in the address book.
 */
public class Keydate implements Comparable<Keydate> {
    public static final String MESSAGE_CONSTRAINTS =
                "Keys should not be blank or only contain whitespaces. Keys and dates should be directly connected by '@'."
                    + " Dates should only be in the format yyyy-MM-dd e.g. Interview@2023-01-01";
    private static final String DATE_ERROR = "Date should be valid.";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public final String fullDate;
    public final String fullKey;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Keydate(String name, String date) {
        requireNonNull(date);
        requireNonNull(name);
        checkArgument(isValidKeydate(new String[]{name, date}), MESSAGE_CONSTRAINTS);
        fullDate = date;
        fullKey = name;
    }

    /**
     * Returns true if a given string array is a valid keydate.
     */
    public static boolean isValidKeydate(String[] test) {
        if (test.length != 2 || test[0] == null || test[1] == null) {
            return false;
        }
        try {
            LocalDate.parse(test[1], dateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the date is in the past.
     */
    public boolean isPastKeydate() {
        try {
            LocalDate date = LocalDate.parse(fullDate, dateFormat);
            if (date.isBefore(LocalDate.now())) {
                return true;
            }
            return false;
        } catch (DateTimeParseException e) {
            // Should not happen
            throw new AssertionError(DATE_ERROR);
        }
    }

    /**
     * Returns true if the date is within the given number of days, inclusive of today.
     */
    public boolean isWithinDays(int days) {
        try {
            LocalDate date = LocalDate.parse(fullDate, dateFormat);
            return !date.isBefore(LocalDate.now()) && !date.isAfter(LocalDate.now().plusDays(days));
        } catch (DateTimeParseException e) {
            // Should not happen
            throw new AssertionError(DATE_ERROR);
        }
    }

    @Override
    public String toString() {
        return fullKey + ": " + fullDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Keydate // instanceof handles nulls
                && fullKey.equals(((Keydate) other).fullKey)
                && fullDate.equals(((Keydate) other).fullDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullKey, fullDate);
    }

    @Override
    public int compareTo(Keydate other) {
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
