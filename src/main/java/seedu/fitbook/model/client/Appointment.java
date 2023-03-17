package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Client's appointment time in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointment(String)}
 */
public class Appointment implements Comparable<Appointment> {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment should be in dd-mm-yyyy HH:mm format.";
    public static final String VALIDATION_REGEX =
            "^(?:0[1-9]|[1-2][0-9]|3[0-1])-(?:0[1-9]|1[0-2])-(?:[0-9]{4}) (?:[01][0-9]|2[0-3]):(?:[0-5][0-9])$";
    public final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final String appointmentTime;
    private final LocalDateTime localDateTime;


    /**
     * Constructs an {@code Appoinment}.
     *
     * @param appointment A valid appointment time.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        checkArgument(isValidAppointment(appointment), MESSAGE_CONSTRAINTS);
        appointmentTime = appointment;
        this.localDateTime = LocalDateTime.parse(appointment, dateTimeFormatter);
    }

    /**
     * Returns true if a given string is a valid appointment time.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherDateTime = (Appointment) other;
        return this.localDateTime.equals(otherDateTime.localDateTime);
    }

    public LocalDateTime getDateTime() {
        return this.localDateTime;
    }

    @Override
    public int hashCode() {
        return appointmentTime.hashCode();
    }

    @Override
    public int compareTo(Appointment other) {
        return this.localDateTime.compareTo(other.localDateTime);
    }

}
