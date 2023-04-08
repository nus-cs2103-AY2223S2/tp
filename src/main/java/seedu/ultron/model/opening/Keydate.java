package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Represents an Opening's date in the address book.
 */
public class Keydate implements Comparable<Keydate> {
    public static final String MESSAGE_CONSTRAINTS = "Keydates should be of the format key@date "
            + "and adhere to the following constraints:\n"
            + "1. The key should only contain alphabetic characters and must not be blank or made of only whitespace.\n"
            + "2. This is followed by a '@' and then a date. The date should only be in the format YYYY-MM-DD.\n"
            + "eg. CodingAssignment@2023-01-01\n";
    private static final String DATE_ERROR = "Date should be valid.";
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String VALIDATION_REGEX_KEY = "^[A-Za-z\\s]*$";

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
        if (test[0].strip().isEmpty() || !test[0].matches(VALIDATION_REGEX_KEY)) {
            return false;
        }
        String date = test[1].strip();
        if (date.length() != 10 || date.charAt(4) != '-' || date.charAt(7) != '-') {
            return false;
        }
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the date is in the past.
     */
    public boolean isPastKeydate() {
        try {
            df.setLenient(false);
            Date date = df.parse(fullDate);
            Calendar calendar = Calendar.getInstance();
            Date currentDate = df.parse(df.format(calendar.getTime()));
            if (date.before(currentDate)) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            // Should not happen
            throw new AssertionError(DATE_ERROR);
        }
    }

    /**
     * Returns true if the date is within the given number of days, inclusive of today.
     */
    public boolean isWithinDays(int days) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentDate = df.parse(df.format(calendar.getTime()));
            calendar.add(Calendar.DATE, days);
            Date endDate = df.parse(df.format(calendar.getTime()));
            df.setLenient(false);
            Date date = df.parse(fullDate);
            return !date.before(currentDate) && !date.after(endDate);
        } catch (ParseException e) {
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
