package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Booking {
    private static final String MESSAGE_CONSTRAINTS = "Appointment booking should be of the format DD-MM-YYYY";
    private final String date;

    public Booking(String date) {
        requireAllNonNull(date);
        checkArgument(isValidBooking(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }
    public boolean isValidBooking(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
