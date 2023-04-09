package seedu.address.model.client.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of an {@code Appointment}.
 */
public class AppointmentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment names should only contain alphanumeric characters and spaces, and it should not be blank, "
                    + "and it should not be longer than 35 characters.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,33}[\\p{Alnum}]?";
    public final String value;

    /**
     * Constructs an empty {@code AppointmentName}.
     */
    public AppointmentName() {
        value = null;
    }

    /**
     * Construct a {@code AppointmentName}.
     * @param name The name of the appointment.
     */
    public AppointmentName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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
        if (an.value == null && value == null) {
            return true;
        }
        if (an.value == null || value == null) {
            return false;
        }
        return value.equals(an.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}



