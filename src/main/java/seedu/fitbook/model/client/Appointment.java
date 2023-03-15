package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's appointment time in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointment(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment should be in dd-mm-yyyy format.";
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
    public final String appointmentTime;

    /**
     * Constructs an {@code Appoinment}.
     *
     * @param appointment A valid appointment time.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        checkArgument(isValidAppointment(appointment), MESSAGE_CONSTRAINTS);
        appointmentTime = appointment;
    }

    /**
     * Returns true if a given string is a valid appointment time.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return '[' + appointmentTime + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && appointmentTime.equals(((Appointment) other).appointmentTime)); // state check
    }

    @Override
    public int hashCode() {
        return appointmentTime.hashCode();
    }

}
