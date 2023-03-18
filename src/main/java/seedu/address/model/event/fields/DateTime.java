package seedu.address.model.event.fields;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's phone number in the Calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Date and Time must be specified! "
                    + "The following format must be adhered: YYYY-MM-DD HHMM e.g. 2023-03-23 1200!";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public final LocalDateTime dateTime;

    public DateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns true if a given string is a valid Date Time.
     */
    public static boolean isValidDateTime(String duration) {
        try {
            LocalDateTime.parse(duration, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateTime.format(dateTimeFormatter);
    }
}
