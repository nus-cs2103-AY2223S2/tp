package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents a Module's timeSlot in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeSlot(String)}
 */
public class TimeSlot implements Comparable<TimeSlot> {

    public static final String MESSAGE_CONSTRAINTS =
            "Timeslot should be of format 'Day StartTime EndTime' (Example: Tuesday 12:00 14:00)";
    public static final String VALIDATION_REGEX = "^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)"
            + "\\s(([01]?[0-9]|2[0-3]):[0-5][0-9])\\s(([01]?[0-9]|2[0-3]):[0-5][0-9])$";
    public DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String storedInputString;


    /**
     * Constructs an {@code TimeSlot}.
     *
     * @param timeSlot A valid timeSlot address.
     */
    public TimeSlot(String timeSlot) {
        requireNonNull(timeSlot);
        String timeSlotTrimmedAndUpperCase = timeSlot.trim().toLowerCase();
        checkArgument(isValidTimeSlot(timeSlotTrimmedAndUpperCase), MESSAGE_CONSTRAINTS);
        processStringToTimeSlot(timeSlotTrimmedAndUpperCase);
        this.storedInputString = timeSlot.trim();

    }

    /**
     * Returns if a given string is a valid timeSlot.
     */
    public static boolean isValidTimeSlot(String test) {
        if (test.equals("none.")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the conversion of String to a LocalDateTime
     * @return LocalDateTime instance
     */
    private void processStringToTimeSlot(String timeslot) {
        if (timeslot.equals("NONE.")) {
            day = null;
            startTime = null;
            endTime = null;

        } else {
            String[] values = timeslot.split(" ");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            day = DayOfWeek.valueOf(values[0]);
            startTime = LocalTime.parse(timeslot, dateTimeFormatter);
            endTime = LocalTime.parse(timeslot, dateTimeFormatter);
        }
    }

    /**
     * Returns String of desired display format
     * @return Display format String
     */
    public String displayFormat() {
        if (day == null) {
            return "None.";
        }
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
        return startTime.hashCode();
    }

    public LocalDateTime getLocalDateTime() {
        //Get the current LocalDateTime of machine
        LocalDateTime timeNow = LocalDateTime.now();

        // Get the date of next occurrence of the day based on time of machine
        LocalDateTime dateOfNextOccurrenceForDay = timeNow.with(TemporalAdjusters.next(this.day));

        // Combine the date of next occurrence and starting time
        LocalDateTime localDateTime = dateOfNextOccurrenceForDay.with(this.startTime);

        return localDateTime;
    }

    @Override
    public int compareTo(TimeSlot otherTimeSlot) {
        return this.getLocalDateTime().compareTo(otherTimeSlot.getLocalDateTime());
    }
}
