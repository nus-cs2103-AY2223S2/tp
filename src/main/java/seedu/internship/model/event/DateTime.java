package seedu.internship.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Parses Date and Time String
 */
public abstract class DateTime implements Comparable<DateTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "Date not in the format DD/MM/YYYY HHMM";

    public static final String NUMERIC_DATE_TIME_FORMAT = "d/M/uuuu HHmm";

    // Fix Adapted from : https://stackoverflow.com/questions/56029619/localdate-is-silently-correcting-bad-dates
    public static final DateTimeFormatter NUMERIC_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern((NUMERIC_DATE_TIME_FORMAT)).withResolverStyle(ResolverStyle.STRICT);

    public static final String ALPHANUMERIC_DATE_TIME_FORMAT = "E,dd'%s' MMMM,yyyy ha";

    public static final DateTimeFormatter ALPHANUMERIC_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(ALPHANUMERIC_DATE_TIME_FORMAT);

    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;


    /**
     * DateTime Constructor Only for the Sentinel Value
     * @param localDateTime
     */
    public DateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.localDate = localDateTime.toLocalDate();
        this.localTime = localDateTime.toLocalTime();
    }

    public boolean isBetween(DateTime start, DateTime end) {
        return localDateTime.isBefore(end.localDateTime) && localDateTime.isAfter(start.localDateTime);
    }

    /**
     * @return LocalDateTime of the DateTime.
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }

    public String getNumericDateTimeString() {
        return NUMERIC_DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * @return Formatted Alphanumeric Date Time String
     */
    @Override
    public String toString() {
        //This was adapted from https://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to
        //-say-11th-21st-or-23rd-ordinal
        int day = localDateTime.getDayOfMonth();
        String daySuff = this.getDaySuffix(day);
        return String.format(ALPHANUMERIC_DATE_TIME_FORMATTER.format(localDateTime), daySuff);
    }

    /**
     * DateTime elemts are compared to on the basis of LocalDateTime object
     * @param other
     * @return
     */
    @Override
    public int compareTo(DateTime other) {
        return localDateTime.compareTo(other.localDateTime);
    }

    /**
     * @param day
     * @return suffix for days
     */
    public String getDaySuffix(int day) {
        if (day == 1 || day == 21 || day == 31) {
            return "st";
        } else if (day == 2 || day == 22) {
            return "nd";
        } else if (day == 3 || day == 23) {
            return "rd";
        } else {
            return "th";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && localDateTime.equals(((DateTime) other).localDateTime)); // state check

    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }

}
