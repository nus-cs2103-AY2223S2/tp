package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.SampleDataUtil.EMPTY_INPUT;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents a Module's timeSlot in the module tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeSlot(String)}
 */
public class TimeSlot implements Comparable<TimeSlot> {

    public static final String MESSAGE_CONSTRAINTS =
            "Timeslot should be of format 'Day-Of-Week HH:MM HH:MM', with the timing being the starting time" +
                    "and the second timing being the ending time. (Example: Tuesday 12:00 14:00)";
    public static final String MESSAGE_STARTTIME_BEFORE_ENDTIME = "Start time must be before end time!";
    public static final String VALIDATION_REGEX = "^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)"
            + "\\s(([01]?[0-9]|2[0-3]):[0-5][0-9])\\s(([01]?[0-9]|2[0-3]):[0-5][0-9])$";
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String storedInputString;

    /**
     * Constructs an {@code TimeSlot}.
     * @param timeSlot A valid timeSlot address.
     */
    public TimeSlot(String timeSlot) {
        requireNonNull(timeSlot);
        String trimmedTimeSlot = timeSlot.trim();
        checkArgument(isValidTimeSlot(trimmedTimeSlot), MESSAGE_CONSTRAINTS);
        checkArgument(isStartTimeBeforeEndTime(trimmedTimeSlot), MESSAGE_STARTTIME_BEFORE_ENDTIME);
        processStringToTimeSlot(trimmedTimeSlot);
    }

    /**
     * Returns if a given string is a valid timeSlot.
     */
    public static boolean isValidTimeSlot(String timeSlot) {
        if (timeSlot.equals(EMPTY_INPUT)) {
            return true;
        }
        return timeSlot.toLowerCase().matches(VALIDATION_REGEX);
        //Important: We need to change the string to lower case because the validation
        //regex is in lower case.
    }

    /**
     * Checks if the start time is before the end time for a timeslot.
     * @param timeSlot A timeslot string.
     * @return a boolean
     */
    public static boolean isStartTimeBeforeEndTime(String timeSlot) {
        if (timeSlot.equals(EMPTY_INPUT)) {
            return true;
        }
        String[] values = timeSlot.split(" ");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(values[1], dateTimeFormatter);
        LocalTime endTime = LocalTime.parse(values[2], dateTimeFormatter);
        return startTime.isBefore(endTime);
    }

    /**
     * Returns the conversion of String to a LocalDateTime
     * @return LocalDateTime instance
     */
    private void processStringToTimeSlot(String timeslot) {
        if (timeslot.equals(EMPTY_INPUT)) {
            day = null;
            startTime = null;
            endTime = null;
            storedInputString = EMPTY_INPUT;

        } else {
            String[] values = timeslot.split(" ");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            day = DayOfWeek.valueOf(values[0].toUpperCase());
            startTime = LocalTime.parse(values[1], dateTimeFormatter);
            endTime = LocalTime.parse(values[2], dateTimeFormatter);
            storedInputString = timeslot;
        }
    }

    /**
     * Returns String of desired display format
     * @return Display format String
     */
    @Override
    public String toString() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, hh:mm a");
        //return formatter.format(storedInputString);
        return storedInputString;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeSlot // instanceof handles nulls
                && day.equals(((TimeSlot) other).day)
                && startTime.equals(((TimeSlot) other).startTime)
                && endTime.equals(((TimeSlot) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return storedInputString.hashCode();
    }

    /**
     * Gets the next occurrence of this timeslot in the form of a LocalDateTime
     * @return a LocalDateTime instance
     */
    public LocalDateTime getLocalDateTime() {
        //Get the current LocalDateTime of machine
        LocalDateTime timeNow = LocalDateTime.now();

        // Get the date of next occurrence of the day based on time of machine
        LocalDateTime dateOfNextOccurrenceForDay = timeNow.with(TemporalAdjusters.next(this.day));

        // Combine the date of next occurrence and starting time
        LocalDateTime localDateTime = dateOfNextOccurrenceForDay.with(this.startTime);

        return localDateTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public int compareTo(TimeSlot otherTimeSlot) {
        return this.getLocalDateTime().compareTo(otherTimeSlot.getLocalDateTime());
    }
}
