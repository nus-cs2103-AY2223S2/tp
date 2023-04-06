package seedu.quickcontacts.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.util.AppUtil.checkArgument;
import static seedu.quickcontacts.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import seedu.quickcontacts.model.meeting.exceptions.InvalidDateTimeFormatException;

/**
 * Represents a Meetings's date/time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime> {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm";

    private static final String DATE_FORMAT_MESSAGE = "dd/MM/yyyy OR DD/MM/YY OR DD/MM with separators being '/', '', '"
            + ".', '-'";
    private static final String TIME_FORMAT_MESSAGE = "HH:mm OR HH:mmAM/PM with separators being ':','','.'";

    public static final String MESSAGE_CONSTRAINTS =
            "Dates/Times should only contain alphanumeric characters and spaces, and it should not be blank"
                    + "and adhere to the following constraints:\n"
                    + "1. Date must comply with the format: " + DATE_FORMAT_MESSAGE + ".\n"
                    + "2. Time must comply with the format: " + TIME_FORMAT_MESSAGE + " in 24-hour format.";

    /**
     * The first character of the date/time string must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * <p>
     * Inputs in addition to formats like dd/MM/yyyy are allowed for semantics
     * such as "next monday".
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{ASCII} ]*";

    private static final String[] DATE_SEPARATORS = {"/", "", ".", "-"};
    private static final String[] TIME_SEPARATORS = {":", "", "."};
    private static final String SEPARATOR_PLACEHOLDER = "{sep}";
    private static final String DDMMYYYY_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM"
            + SEPARATOR_PLACEHOLDER + "uuuu";
    private static final String DDMMYY_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM"
            + SEPARATOR_PLACEHOLDER + "uu";
    private static final String DDMM_TEMPLATE = "dd" + SEPARATOR_PLACEHOLDER + "MM";
    private static final String HHMM_TEMPLATE = "HH" + SEPARATOR_PLACEHOLDER + "mm";
    private static final String HHMM_AM_PM_TEMPLATE = "h" + SEPARATOR_PLACEHOLDER + "mma";

    private final LocalDate meetingDate;
    private final Optional<LocalTime> meetingTime;

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
            meetingTime = Optional.of(stringToLocalTime(timePortion));
        } else {
            meetingTime = Optional.empty();
        }

        meetingDuration = Duration.ZERO;
    }

    /**
     * Constructs a {@code DateTime} with a duration specified.
     *
     * @param startDateTime A valid start date/time.
     * @param endDateTime   A valid end date/time.
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
            meetingTime = Optional.of(stringToLocalTime(startTimePortion));

            LocalDateTime startDT = LocalDateTime.of(meetingDate, meetingTime.get());
            LocalDateTime endDT = LocalDateTime.of(stringToLocalDate(endDatePortion),
                    stringToLocalTime(endTimePortion));

            checkArgument(isValidDuration(startDT, endDT));
            meetingDuration = Duration.between(startDT, endDT);
        } else {
            meetingTime = Optional.empty();
            meetingDuration = Duration.between(meetingDate, stringToLocalDate(endDatePortion));
        }
    }

    /**
     * Returns a {@code string} representation of the Date and Time (if exists) stored
     * in this class.
     */
    public String getDateTime() {
        String timePortion = meetingTime
                .map(localTime -> " " + localTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                .orElse("");

        return meetingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + timePortion;
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
        return test.matches(VALIDATION_REGEX) && stringToLocalDate(test) != LocalDate.MAX;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    private static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX) && stringToLocalTime(test) != LocalTime.MAX;
    }

    private static LocalDate stringToLocalDate(String date) {
        try {
            return parseDate(date, getDateFormat(date));
        } catch (InvalidDateTimeFormatException e) {
            return LocalDate.MAX;
        }
    }

    private static LocalTime stringToLocalTime(String time) {
        try {
            return parseTime(time, getTimeFormat(time));
        } catch (InvalidDateTimeFormatException e) {
            return LocalTime.MAX;
        }
    }

    // Idea adapted from
    // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static LocalDate parseDate(String date, String pattern) {
        try {
            if (!pattern.equals("ddMM")) {
                return LocalDate.parse(date,
                        DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH).withResolverStyle(ResolverStyle.STRICT));
            }

            if (date.length() != 4 && date.length() != 5) {
                throw new InvalidDateTimeFormatException();
            }

            String newDate = date + Year.now();
            if (date.length() == 5) {
                newDate = date + date.charAt(2) + Year.now();
            }

            return LocalDate.parse(newDate,
                    DateTimeFormatter.ofPattern(getDateFormat(newDate), Locale.ENGLISH)
                        .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException | InvalidDateTimeFormatException e) {
            return LocalDate.MAX;
        }
    }

    // Idea adapted from
    // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static LocalTime parseTime(String time, String pattern) {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            return LocalTime.MAX;
        }
    }

    // Idea adapted from
    // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static String getDateFormat(String date) throws InvalidDateTimeFormatException {
        String dateFormat = "";
        for (String separator : DATE_SEPARATORS) {
            String ddmmyyyyPattern = patternForSeparator(DDMMYYYY_TEMPLATE, separator);
            String ddmmyyPattern = patternForSeparator(DDMMYY_TEMPLATE, separator);
            String ddmmPattern = patternForSeparator(DDMM_TEMPLATE, separator);

            if (parseDate(date, ddmmyyyyPattern) != LocalDate.MAX) {
                dateFormat = ddmmyyyyPattern;
            }

            if (parseDate(date, ddmmyyPattern) != LocalDate.MAX) {
                dateFormat = ddmmyyPattern;
            }

            if (parseDate(date, ddmmPattern) != LocalDate.MAX) {
                dateFormat = ddmmPattern;
            }
        }

        if (dateFormat.isEmpty()) {
            throw new InvalidDateTimeFormatException();
        }

        return dateFormat;
    }

    // Idea adapted from
    // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static String getTimeFormat(String time) throws InvalidDateTimeFormatException {
        String timeFormat = "";
        for (String separator : TIME_SEPARATORS) {
            String hhmmPattern = patternForSeparator(HHMM_TEMPLATE, separator);
            String hhmmampmPattern = patternForSeparator(HHMM_AM_PM_TEMPLATE, separator);

            if (parseTime(time, hhmmPattern) != LocalTime.MAX) {
                timeFormat = hhmmPattern;
            }

            if (parseTime(time, hhmmampmPattern) != LocalTime.MAX) {
                timeFormat = hhmmampmPattern;
            }
        }

        if (timeFormat.isEmpty()) {
            throw new InvalidDateTimeFormatException();
        }

        return timeFormat;
    }

    // Idea adapted from
    // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static String patternForSeparator(String template, String sep) {
        return template.replace(SEPARATOR_PLACEHOLDER, sep);
    }

    /**
     * Returns {@code LocalDateTime} representation of the DateTime stored.
     * If time is not specified, it will default to 00:00 (12AM).
     *
     * @return {@code LocalDateTime} representation of the DateTime stored.
     */
    public LocalDateTime get() {
        return LocalDateTime.of(meetingDate, meetingTime.orElse(LocalTime.of(0, 0)));
    }

    @Override
    public String toString() {
        if (meetingDuration != null && !meetingDuration.isZero()) {
            String start = meetingTime.orElse(LocalTime.of(0, 0))
                    .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            String end = LocalDateTime.of(meetingDate, meetingTime.orElse(LocalTime.of(0, 0))).plus(meetingDuration)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT));
            return meetingDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                    + " "
                    + start
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

    @Override
    public int compareTo(DateTime o) {
        if (meetingDate.isEqual(o.meetingDate)) {
            return this.meetingTime
                    .orElse(LocalTime.of(0, 0))
                    .compareTo(o.meetingTime
                            .orElse(LocalTime.of(0, 0)));
        }
        return meetingDate.compareTo(o.meetingDate);
    }
}
