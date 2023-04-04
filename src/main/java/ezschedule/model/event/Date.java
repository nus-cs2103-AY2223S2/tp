package ezschedule.model.event;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import ezschedule.commons.util.AppUtil;
import ezschedule.model.event.exceptions.InvalidDateException;

/**
 * Represents an Event's date in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Date should only contain numeric characters, "
            + "follows the format yyyy-MM-dd (year from 0001, month from 01 to 12, and day from 01 to 31), "
            + "and it should not be blank";

    public static final String VALIDATION_REGEX = "((000[1-9]|[1-9]\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        AppUtil.checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        try {
            this.date = LocalDate.parse(date, formatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            String message = String.format("Date %1$s is invalid", date);
            throw new InvalidDateException(message);
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the number of days between two valid dates.
     */
    public long getDaysBetween(LocalDate comparingDate) {
        return DAYS.between(date, comparingDate);
    }

    /**
     * Returns the number of months between two valid dates.
     */
    public long getMonthsBetween(LocalDate comparingDate) {
        return MONTHS.between(date, comparingDate);
    }

    /**
     * Returns true if date has passed.
     */
    public boolean isPastDate() {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Returns true if date has yet to pass.
     */
    public boolean isFutureDate() {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Returns the year value of date.
     */
    public int getYear() {
        return date.getYear();
    }

    /**
     * Returns the month value of date.
     */
    public int getMonth() {
        return date.getMonthValue();
    }

    /**
     * Returns the day value of date.
     */
    public int getDay() {
        return date.getDayOfMonth();
    }

    @Override
    public int compareTo(Date otherDate) {
        return date.compareTo(otherDate.date);
    }

    @Override
    public String toString() {
        return date.toString();
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
}
