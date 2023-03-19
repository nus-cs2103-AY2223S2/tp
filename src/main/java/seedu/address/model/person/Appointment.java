package seedu.address.model.person;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should in the format of yyyy-MM-dd HHmm";

    // treat age also as a string
    private LocalDateTime time = null;
    private boolean isDone;

    /**
     * Constructs a {@code Name}.
     *
     * @param time A valid time
     */
    public Appointment(LocalDateTime time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = time;
        this.isDone = false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTime(LocalDateTime time) {
        // since all check have been done before time has been converted from String to LocalDateTime
        return true;
    }
    public LocalDateTime getTime() {
        return time;
    }
    @Override
    public String toString() {
        return "Next appointment time: " + time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && time.equals(((Appointment) other).time)); // state check
    }
    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
