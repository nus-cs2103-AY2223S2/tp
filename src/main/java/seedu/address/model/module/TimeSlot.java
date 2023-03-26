package seedu.address.model.module;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Module's timeSlot in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeSlot(String)}
 */
public class TimeSlot {
    public static final String MESSAGE_CONSTRAINTS =
            "Timeslot should be of format \"ddMMyyyy HH:mm\" (Example: 230223 18:00)";
    public static final String VALIDATION_REGEX = "^[0-9]{6}\\s[0-9]{2}:[0-9]{2}$";

    public final LocalDateTime value;

    /**
     * Constructs an {@code TimeSlot}.
     *
     * @param timeSlot A valid timeSlot address.
     */
    public TimeSlot(String timeSlot) {
        requireNonNull(timeSlot);
        checkArgument(isValidTimeSlot(timeSlot), MESSAGE_CONSTRAINTS);
        value = convertStringToDate(timeSlot);
    }

    /**
     * Returns if a given string is a valid timeSlot.
     */
    public static boolean isValidTimeSlot(String test) {
        if (test.equals("None.")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the conversion of String to a LocalDateTime
     * @return LocalDateTime instance
     */
    private LocalDateTime convertStringToDate(String timeslot) {
        if (timeslot.equals("None.")) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy HH:mm");
        return LocalDateTime.parse(timeslot, dateTimeFormatter);
    }

    /**
     * Returns String of desired display format
     * @return Display format String
     */
    public String displayFormat() {
        if (value == null) {
            return "None.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, hh:mm a");
        return formatter.format(value);
    }

    @Override
    public String toString() {
        if (value == null) {
            return "None.";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy HH:mm");
        return dateTimeFormatter.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeSlot // instanceof handles nulls
                && value.equals(((TimeSlot) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
