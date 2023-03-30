package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Client's weight history date time in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateFormat(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in dd-mm-yyyy HH:mm format.";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date should be valid.";
    public static final String VALIDATION_REGEX =
            "^(?:0[1-9]|[1-2][0-9]|3[0-1])-(?:0[1-9]|1[0-2])-(?:[0-9]{4}) (?:[01][0-9]|2[0-3]):(?:[0-5][0-9])$";
    public final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public final DateTimeFormatter dateTimeFormatterForStr = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");

    public final String dateTime;
    public final LocalDateTime localDateTime;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date time.
     */
    public Date(String date) {
        requireNonNull(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(date), MESSAGE_DATE_CONSTRAINTS);
        dateTime = date;
        this.localDateTime = LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        assert date != null : "Date is null";
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime.parse(date, formatter);
            return date.matches(VALIDATION_REGEX);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date time format.
     */
    public static boolean isValidDateFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDateTime = (Date) other;
        return this.localDateTime.equals(otherDateTime.localDateTime);
    }

    public LocalDateTime getDateTime() {
        return this.localDateTime;
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public int compareTo(Date other) {
        return this.localDateTime.compareTo(other.localDateTime);
    }
}
