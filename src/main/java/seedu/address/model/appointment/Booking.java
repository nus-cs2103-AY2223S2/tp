package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an appointment booking.
 */
public class Booking {

    public static final String MESSAGE_CONSTRAINTS = "Appointment booking should adhere to the following conventions: "
            + "\n"
            + "1. Format should be DD-MM-YYYY HH:mm\n"
            + "2.'DD' and 'MM' values should be valid for the calendar\n"
            + "3. HH:mm should follow the 24-hour notation";
    private final String date;

    /**
     * Constructs an {@code Booking}
     * @param date
     */
    public Booking(String date) {
        requireNonNull(date);
        checkArgument(isValidBookingFormat(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Checks if the input date is in a valid format.
     * @param someDate
     * @return true if is in a valid format, false otherwise
     */
    public static boolean isValidBookingFormat(String someDate) {
        try {
            LocalDate.parse(someDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return date;
    }
}
