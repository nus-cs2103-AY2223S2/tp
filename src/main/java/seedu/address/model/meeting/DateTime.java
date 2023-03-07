package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.model.meeting.exceptions.InvalidEndDateTimeException;

/**
 * Represents a Meetings's date/time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 *
 * TODO: Include more acceptable formats for date/time instead of just dd/MM/yyyy HH:mm
 */
public class DateTime {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm";

    public static final String MESSAGE_CONSTRAINTS =
            "Dates/Times should only contain alphanumeric characters and spaces, and it should not be blank"
            + "and adhere to the following constraints:\n"
            + "1. Date must comply with the format: " + DATE_FORMAT + ".\n"
            + "2. Time must comply with the format: " + TIME_FORMAT + " in 24-hour format.";

    /**
     * The first character of the date/time string must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * <p>
     * Inputs in addition to formats like dd/MM/yyyy are allowed for semantics
     * such as "next monday".
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{ASCII} ]*";

    private final LocalDateTime meetingDateTime;

    /**
     * This is used only for inputs that include specific durations.
     * For example Thursday 2pm - Thursday 4pm implies duration of
     * 2 hours.
     */
    private final Duration meetingDuration;

    /**
     * Constructs a {@code DateTime} without any duration specified.
     *
     * @param dateTime A valid date/time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        try {
            meetingDateTime = LocalDateTime.parse(dateTime,
                    DateTimeFormatter.ofPattern(String.format("%s %s", DATE_FORMAT, TIME_FORMAT)));
            meetingDuration = Duration.ZERO;
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Unable to recognise '" + dateTime + "' as valid date/time.");
        }
    }

    /**
     * Constructs a {@code DateTime} with a duration specified.
     *
     * @param startDateTime A valid start date/time.
     * @param endDateTime A valid end date/time.
     */
    public DateTime(String startDateTime, String endDateTime) {
        requireAllNonNull(startDateTime, endDateTime);
        checkArgument(isValidDateTime(startDateTime), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateTime(endDateTime), MESSAGE_CONSTRAINTS);

        try {
            meetingDateTime = LocalDateTime.parse(startDateTime,
                    DateTimeFormatter.ofPattern(String.format("%s %s", DATE_FORMAT, TIME_FORMAT)));
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Unable to recognise '" + startDateTime + "' as valid date/time.");
        }

        try {
            LocalDateTime end = LocalDateTime.parse(endDateTime,
                    DateTimeFormatter.ofPattern(String.format("%s %s", DATE_FORMAT, TIME_FORMAT)));
            Duration duration = Duration.between(meetingDateTime, end);

            if (duration.isNegative() || duration.isZero()) {
                throw new InvalidEndDateTimeException();
            } else {
                meetingDuration = duration;
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Unable to recognise '" + endDateTime + "' as valid date/time.");
        } catch (DateTimeException | ArithmeticException e) {
            throw new DateTimeException("Unable to calculate the duration between " + startDateTime
                    + " and " + endDateTime);
        }
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String end = meetingDuration != null ? meetingDateTime.plus(meetingDuration).toString() : "";

        if (!end.isEmpty()) {
            return meetingDateTime + " to " + end;
        }

        return meetingDateTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && meetingDateTime.equals(((DateTime) other).meetingDateTime) // state check
                && meetingDuration.equals(((DateTime) other).meetingDuration)); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingDateTime, meetingDuration);
    }
}
