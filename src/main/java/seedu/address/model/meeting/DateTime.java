package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

import seedu.address.model.meeting.exceptions.InvalidDateTimeFormatException;

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

    private static final String[] DATE_SEPARATORS = {"/", "", ".", "-"};
    private static final String[] TIME_SEPARATORS = {":", "", "."};
    private static final String SEPARATOR_PLACEHOLDER = "{sep}";
    private static final String DDMMYYYY_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM"
            + SEPARATOR_PLACEHOLDER + "yyyy";
    private static final String DDMMYY_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM"
            + SEPARATOR_PLACEHOLDER + "yy";
    private static final String DDMM_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM";
    private static final String HHMM_TEMPLATE = "HH" + SEPARATOR_PLACEHOLDER + "mm";
    private static final String HHMM_AM_PM_TEMPLATE = "h" + SEPARATOR_PLACEHOLDER + "mma";

    private final LocalDate meetingDate;

    /**
     * If meetingTime is not specified in the input, {@code meetingTime} will
     * be set to {@code LocalTime.MIN}.
     */
    private final LocalTime meetingTime;

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

        String[] dtSplit = dateTime.split(" ");

        String datePortion = dtSplit[0];
        meetingDate = stringToLocalDate(datePortion);

        if (dtSplit.length == 2) {
            String timePortion = dtSplit[1];
            meetingTime = stringToLocalTime(timePortion);
        } else {
            meetingTime = LocalTime.MIN;
        }

        meetingDuration = Duration.ZERO;
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

        String[] startDtSplit = startDateTime.split(" ");
        String[] endDtSplit = endDateTime.split(" ");

        String startDatePortion = startDtSplit[0];
        String endDatePortion = endDtSplit[0];
        meetingDate = stringToLocalDate(startDatePortion);

        if (startDtSplit.length == 2 && endDtSplit.length == 2) {
            String startTimePortion = startDtSplit[1];
            String endTimePortion = endDtSplit[1];
            meetingTime = stringToLocalTime(startTimePortion);

            LocalDateTime startDT = LocalDateTime.of(meetingDate, meetingTime);
            LocalDateTime endDT = LocalDateTime.of(stringToLocalDate(endDatePortion),
                    stringToLocalTime(endTimePortion));

            checkArgument(isValidDuration(startDT, endDT));
            meetingDuration = Duration.between(startDT, endDT);
        } else {
            meetingTime = LocalTime.MIN;
            meetingDuration = Duration.between(meetingDate, stringToLocalDate(endDatePortion));
        }
    }

    /**
     * Returns a {@code string} representation of the Date and Time (if exists) stored
     * in this class.
     */
    public String getDateTime() {
        if (!meetingTime.equals(LocalTime.MIN)) {
            return meetingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                    + " "
                    + meetingTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        }

        return meetingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Returns true if a given string is a valid date/time.
     */
    public static boolean isValidDateTime(String test) {
        String[] dtSplit = test.split(" ");

        if (dtSplit.length > 2 || !test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String datePortion = dtSplit[0];
        if (dtSplit.length == 1) {
            return isValidDate(datePortion);
        }

        String timePortion = dtSplit[1];

        return isValidDate(datePortion) && isValidTime(timePortion);
    }

    /**
     * Returns true if a given string is a valid date/time.
     */
    public static boolean isValidDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return !Duration.between(startDateTime, endDateTime).isNegative()
                && !Duration.between(startDateTime, endDateTime).isZero();
    }

    /**
     * Returns true if a given string is a valid date.
     */
    private static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX) && !stringToLocalDate(test).equals(LocalDate.MIN);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    private static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX) && !stringToLocalTime(test).equals(LocalTime.MIN);
    }

    private static LocalDate stringToLocalDate(String date) {
        try {
            return parseDate(date, getDateFormat(date));
        } catch (InvalidDateTimeFormatException e) {
            return LocalDate.MIN;
        }
    }

    private static LocalTime stringToLocalTime(String time) {
        try {
            return parseTime(time, getTimeFormat(time));
        } catch (InvalidDateTimeFormatException e) {
            return LocalTime.MIN;
        }
    }

    private static LocalDate parseDate(String date, String pattern) {
        try {
            if (!pattern.equals("ddMM")) {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
            }

            if (date.length() != 4 && date.length() != 5) {
                throw new InvalidDateTimeFormatException();
            }

            String newDate = date + Year.now();
            if (date.length() == 5) {
                newDate = date + date.charAt(2) + Year.now();
            }

            return LocalDate.parse(newDate, DateTimeFormatter.ofPattern(getDateFormat(newDate), Locale.ENGLISH));
        } catch (DateTimeParseException | InvalidDateTimeFormatException e) {
            return LocalDate.MIN;
        }
    }

    private static LocalTime parseTime(String time, String pattern) {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            return LocalTime.MIN;
        }
    }

    private static String getDateFormat(String date) throws InvalidDateTimeFormatException {
        String dateFormat = "";
        for (String separator : DATE_SEPARATORS) {
            String ddmmyyyyPattern = patternForSeparator(DDMMYYYY_TEMPLATE, separator);
            String ddmmyyPattern = patternForSeparator(DDMMYY_TEMPLATE, separator);
            String ddmmPattern = patternForSeparator(DDMM_TEMPLATE, separator);

            if (!parseDate(date, ddmmyyyyPattern).isEqual(LocalDate.MIN)) {
                dateFormat = ddmmyyyyPattern;
            }

            if (!parseDate(date, ddmmyyPattern).isEqual(LocalDate.MIN)) {
                dateFormat = ddmmyyPattern;
            }

            if (!parseDate(date, ddmmPattern).isEqual(LocalDate.MIN)) {
                dateFormat = ddmmPattern;
            }
        }

        if (dateFormat.isEmpty()) {
            throw new InvalidDateTimeFormatException();
        }

        return dateFormat;
    }

    private static String getTimeFormat(String time) throws InvalidDateTimeFormatException {
        String timeFormat = "";
        for (String separator : TIME_SEPARATORS) {
            String hhmmPattern = patternForSeparator(HHMM_TEMPLATE, separator);
            String hhmmampmPattern = patternForSeparator(HHMM_AM_PM_TEMPLATE, separator);

            if (!parseTime(time, hhmmPattern).equals(LocalTime.MIN)) {
                timeFormat = hhmmPattern;
            }

            if (!parseTime(time, hhmmampmPattern).equals(LocalTime.MIN)) {
                timeFormat = hhmmampmPattern;
            }
        }

        if (timeFormat.isEmpty()) {
            throw new InvalidDateTimeFormatException();
        }

        return timeFormat;
    }

    private static String patternForSeparator(String template, String sep) {
        return template.replace(SEPARATOR_PLACEHOLDER, sep);
    }

    @Override
    public String toString() {
        String end = meetingDuration != null && !meetingDuration.isZero()
                ? LocalDateTime.of(meetingDate, meetingTime).plus(meetingDuration).format(
                        DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT))
                : "";

        if (!end.isEmpty()) {
            return meetingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                    + " "
                    + meetingTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT))
                    + " to "
                    + end;
        }

        return getDateTime();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && meetingDate.equals(((DateTime) other).meetingDate) // state check
                && meetingTime.equals(((DateTime) other).meetingTime) // state check
                && meetingDuration.equals(((DateTime) other).meetingDuration)); // state check
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingDate, meetingTime, meetingDuration);
    }
}
