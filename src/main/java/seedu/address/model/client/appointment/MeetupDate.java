package seedu.address.model.client.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A class that represents the meetup date of an {@code Appointment} in the format of dd.mm.yyyy.
 */
public class MeetupDate {
    public static final String MESSAGE_CONSTRAINTS =
            "date should only contain numbers, in the format of dd.mm.yyyy";

    public static final String VALIDATION_REGEX = "^\\d{2}\\.\\d{2}.\\d{4}$";

    public static final String MESSAGE_PAST_DATE = "Appointment dates have to be scheduled in advanced."
            + " Today's date is " + LocalDate.now();

    public final LocalDate meetupDate;

    /**
     * Constructs an empty {@code MeetupDate}.
     */
    public MeetupDate() {
        meetupDate = null;
    }

    /**
     * Constructs a {@code MeetupDate}
     * @param meetupDate The meetup date of the appointment.
     */
    public MeetupDate(String meetupDate) {
        requireNonNull(meetupDate);
        checkArgument(isValidDate(meetupDate), MESSAGE_CONSTRAINTS);
        checkArgument(!isFutureDate(meetupDate), MESSAGE_PAST_DATE);
        this.meetupDate = stringToDate(meetupDate);
    }


    /**
     * Converts a string into a LocalDate object.
     * String has to be in the format of dd.MM.yyyy.
     *
     * @param date String to be converted into LocalDate object.
     * @return LocalDate object.
     */
    public static LocalDate stringToDate(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }

    /**
     * Returns true if the given date is after the current date.
     * @param date The appointment date.
     */
    public static boolean isFutureDate(String date) {
        LocalDate localDate = stringToDate(date);
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(localDate);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        boolean valid = true;
        try {
            LocalDate localDate = stringToDate(date);
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return (date.matches(VALIDATION_REGEX) && valid);
    }

    public String getDisplayString() {
        if (meetupDate == null) {
            return null;
        }
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return outputFormat.format(meetupDate);
    }

    @Override
    public String toString() {
        if (meetupDate == null) {
            return null;
        }
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return outputFormat.format(meetupDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MeetupDate)) {
            return false;
        }

        MeetupDate md = (MeetupDate) other;
        if (md.meetupDate == null && meetupDate == null) {
            return true;
        }
        if (md.meetupDate == null || meetupDate == null) {
            return false;
        }
        return meetupDate.equals(md.meetupDate);
    }

    @Override
    public int hashCode() {
        return meetupDate.hashCode();
    }

}
