package seedu.address.model.event.fields;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Represents an Event's phone number in the Calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Date and Time must be specified! "
                    + "The following format must be adhered: YYYY-MM-DD HHMM e.g. 2023-03-23 1200!";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime dateTime;

    /**
     * Creates a {@code DateTime} with the given {@code LocalDateTime}.
     */
    public DateTime(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime);
        this.dateTime = dateTime;
    }

    public DateTime(String dateTime) {
        this(LocalDateTime.parse(dateTime, dateTimeFormatter));
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
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isValidInterval(DateTime startDateTime, DateTime endDateTime) {
        return startDateTime.getDateTime().isBefore(endDateTime.getDateTime());
    }

    public static long getIntervalDuration(DateTime startDateTime, DateTime endDateTime, ChronoUnit unit) {
        return startDateTime.getDateTime().until(endDateTime.getDateTime(), unit);
    }

    /**
     * Increments {@code dateTime} by 1 unit of {@code incrementalUnit}.
     */
    public void plus(ChronoUnit incrementalUnit) {
        this.dateTime = this.dateTime.plus(1, incrementalUnit);
    }

    public String toString(DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return dateTime.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DateTime)) {
            return false;
        }
        return dateTime.equals(((DateTime) other).dateTime);
    }
}
