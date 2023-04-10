package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents an Appointment's starting and ending date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeslot(String)}
 */
public class Timeslot {
    public static final String MESSAGE_CONSTRAINTS =
        "The starting and ending LocalDateTime should follow the format ddMMyyyy HH:mm (day month year hour minutes),"
            + " and it should not be blank";

    public static final String MESSAGE_START_AFTER_END =
        "The starting time should be before the ending time";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String TIMESLOT_VALIDATION_REGEX = "\\d{8} \\d{2}:\\d{2},\\s?\\d{8} \\d{2}:\\d{2}";
    public static final String DATETIME_VALIDATION_REGEX = "\\d{8} \\d{2}:\\d{2}";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM HH:mm");
    public final LocalDateTime startingDateTime;
    public final LocalDateTime endingDateTime;
    public final String timeslotString;

    /**
     * Constructs a {@code Timeslot}.
     *
     * @param timeslot A valid string containing both the start and end time of the timeslot.
     * @throws IllegalArgumentException if the starting time is not before the ending time.
     */
    public Timeslot(String timeslot) throws IllegalArgumentException {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        // strip any leading/trailing spaces from each datetime string
        String[] dateTimes = Arrays.stream(splitIntoStartAndEnd(timeslot))
                .map(String::strip).toArray(String[]::new);
        this.timeslotString = timeslot;
        this.startingDateTime = LocalDateTime.parse(dateTimes[0], dateTimeFormatter);
        this.endingDateTime = LocalDateTime.parse(dateTimes[1], dateTimeFormatter);
        checkArgument(startingDateTime.isBefore(endingDateTime), MESSAGE_START_AFTER_END);
    }

    /**
     * Constructs a {@code Timeslot}.
     *
     * @param startingDateTime The start time.
     * @param endingDateTime   The end time.
     */
    public Timeslot(LocalDateTime startingDateTime, LocalDateTime endingDateTime) {
        this.startingDateTime = startingDateTime;
        this.endingDateTime = endingDateTime;
        this.timeslotString =
            startingDateTime.format(displayFormat) + " to " + endingDateTime.format(displayFormat);
    }

    /**
     * Splits a valid timeslot string into its constituent LocalDateTime strings.
     *
     * @param timeslot A valid string for constructing a timeslot.
     * @return Array of 2 elements, the starting date time and the ending date time.
     */
    private String[] splitIntoStartAndEnd(String timeslot) {
        return timeslot.split(",");
    }

    /**
     * Checks if this timeslot and the provided timeslot overlap.
     *
     * @param otherTimeslot The timeslot to check for overlaps with.
     * @return Whether both timeslots clash.
     */
    public boolean hasOverlap(Timeslot otherTimeslot) {
        return this.startingDateTime.equals(otherTimeslot.startingDateTime)
            || this.endingDateTime.equals(otherTimeslot.endingDateTime)
            || (this.startingDateTime.isBefore(otherTimeslot.endingDateTime)
            && otherTimeslot.startingDateTime.isBefore(this.endingDateTime));
    }

    /**
     * Checks if the first datetime is before or equal to the second datetime.
     *
     * @param thisTime The first datetime that should be before or equal to the second datetime.
     * @param thatTime The second datetime.
     * @return if the first datetime is before or equal to the second datetime.
     */
    private boolean isBeforeOrEqual(LocalDateTime thisTime, LocalDateTime thatTime) {
        return thisTime.isBefore(thatTime) || thisTime.isEqual(thatTime);
    }

    /**
     * Returns true if a given string is a valid timeslot.
     */
    public static boolean isValidTimeslot(String test) {
        return test.matches(TIMESLOT_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDatetime(String test) {
        return test.matches(DATETIME_VALIDATION_REGEX);
    }

    /**
     * Returns the date time formatter used for parsing all date time strings.
     *
     * @return A date time formatter for parsing date time strings.
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public String getFormattedStartTime() {
        return startingDateTime.format(displayFormat);
    }

    public String getFormattedEndTime() {
        return endingDateTime.format(displayFormat);
    }
    @Override
    public String toString() {
        return getFormattedStartTime() + " to " + getFormattedEndTime();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Timeslot // instanceof handles nulls
            && startingDateTime.equals(((Timeslot) other).startingDateTime)
            && endingDateTime.equals(((Timeslot) other).endingDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDateTime, endingDateTime);
    }
}
