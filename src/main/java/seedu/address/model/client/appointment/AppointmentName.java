package seedu.address.model.client.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of an {@code Appointment}.
 */
public class AppointmentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String appointmentName;

    /**
     * Constructs an empty {@code AppointmentName}.
     */
    public AppointmentName() {
        appointmentName = null;
    }

    /**
     * Construct a {@code AppointmentName}.
     * @param name The name of the appointment.
     */
    public AppointmentName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        appointmentName = name;
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return appointmentName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AppointmentName)) {
            return false;
        }

        AppointmentName an = (AppointmentName) other;
        if (an.appointmentName == null && appointmentName == null) {
            return true;
        }
        if (an.appointmentName == null || appointmentName == null) {
            return false;
        }
        return appointmentName.equals(an.appointmentName);
    }

    @Override
    public int hashCode() {
        return appointmentName.hashCode();
    }
}



