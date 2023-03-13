package seedu.address.model.appointment;

import seedu.address.logic.parser.exceptions.ParseException;

import java.text.SimpleDateFormat;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Booking {
    public static final String MESSAGE_CONSTRAINTS = "Appointment booking should be of the format DD-MM-YYYY HH:MM";
    private final String date;

    public Booking(String date) {
        requireNonNull(date);
        checkArgument(isValidBooking(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }
    public static boolean isValidBooking(String someDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            simpleDateFormat.parse(someDate);
            return true;
        } catch (java.text.ParseException e) { //
            return false;
        }
    }

    public String getBookingDate() { return date; }
}
