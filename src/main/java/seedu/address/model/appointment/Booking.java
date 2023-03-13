package seedu.address.model.appointment;

import seedu.address.logic.parser.exceptions.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Booking {
    
    public static final String MESSAGE_CONSTRAINTS = "Appointment booking should adhere to the following conventions: \n" +
            "1. Format should be DD-MM-YYYY HH:mm\n" +
            "2.'DD' and 'MM' values should be valid for the calendar\n" +
            "3. HH:mm should follow the 24-hour notation";
    private final String date;

    public Booking(String date) {
        requireNonNull(date);
        checkArgument(isValidBookingFormat(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    public static boolean isValidBookingFormat(String someDate) {
        try {
            LocalDate.parse(someDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    /*
    public static boolean isValidBookingConvention(String someDate) {
        try {
            LocalDate localDate = LocalDate.parse(someDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            boolean isValidDay = (day >= 1 && day <= localDate.lengthOfMonth());
            boolean isValidMonth = (month >= 1 && month <= 12);
            boolean isValidYear = (year >= 0);
            boolean isValidCalendar = isValidDay && isValidMonth && isValidYear;

            LocalDateTime localDateTime = LocalDateTime.parse(someDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

            int hour = localDateTime.getHour();
            int minutes = localDateTime.getMinute();
            boolean isValidHour = (hour >= 0 && hour <= 24);
            boolean isValidMinutes = (minutes >= 0 && minutes <= 59);
            boolean isValidClockNotation = isValidHour && isValidMinutes;
            return (isValidClockNotation && isValidCalendar);
        } catch (DateTimeParseException e) {
            return false;
        }
    }*/

    public String getBookingDate() { return date; }
}
