package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an Appointment's starting and ending date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeslot(String)}
 */
public class Timeslot {
    public static final String MESSAGE_CONSTRAINTS =
        "The starting and ending LocalDateTime should follow the format ddMMyyyy HH:mm (day month year hour minutes),"
            + " and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{8} \\d{2}:\\d{2},\\d{8} \\d{2}:\\d{2}";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");

    public final LocalDateTime startingDateTime;
    public final LocalDateTime endingDateTime;

    /**
     * Constructs a {@code Timeslot}.
     *
     * @param timeslot A valid string containing both the start and end time of the timeslot.
     */
    public Timeslot(String timeslot) {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        String[] dateTimes = splitIntoStartAndEnd(timeslot);
        this.startingDateTime = LocalDateTime.parse(dateTimes[0], dateTimeFormatter);
        this.endingDateTime = LocalDateTime.parse(dateTimes[1], dateTimeFormatter);
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
     * Returns true if a given string is a valid timeslot.
     */
    public static boolean isValidTimeslot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return startingDateTime.toString();
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
