package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a Session in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session {

    public static final String MESSAGE_CONSTRAINTS = "Start date time should be before end date time.";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final String startDateTime;
    private final String endDateTime;
    public String command;

    /**
     * Every field must be present and not null.
     * @param startDateTime
     * @param endDateTime
     */
    public Session(String startDateTime, String endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        if (!this.isValidSession()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public Session(String sessionString) throws IllegalValueException {
        String[] parts = sessionString.split(" to ");
        if (parts.length != 2) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }

        String startDateTime = parts[0];
        String endDateTime = parts[1];

        if (!Session.isValidDateTimeFormat(startDateTime) || !Session.isValidDateTimeFormat(endDateTime)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    /**
     * Returns true if the given string is a valid date format.
     * The date format is "dd-MM-yyyy".
     */
    public static boolean isValidDateFormat(String str) {
        try {
            LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    /**
     * Returns true if the given string is a valid time format, i.e. "HH:mm".
     */
    public static boolean isValidTimeFormat(String test) {
        if (test == null || test.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }







    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns true if a given string is a valid date time format.
     */
    public static boolean isValidDateTimeFormat(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if both sessions have the same identity and data fields.
     * This defines a stronger notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getStartDateTime().equals(getStartDateTime())
                && otherSession.getEndDateTime().equals(getEndDateTime());
    }

    /**
     * Returns true if both sessions have the same identity and data fields.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return  otherSession.getStartDateTime().equals(getStartDateTime())
                && otherSession.getEndDateTime().equals(getEndDateTime());
    }

    public boolean isValidSession() {
        return LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).isBefore(LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Returns the duration of the session.
     * @return duration of the session.
     */
    public Duration getSessionDuration() {
        return Duration.between(LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Start Date Time: ")
                .append(LocalDateTime.parse(getStartDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append("\n End Date Time: ")
                .append(LocalDateTime.parse(getEndDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER));
        return builder.toString();
    }

    public String getCommand(){
        return startDateTime + " to " + endDateTime;
    }

    public String toCommandString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(LocalDateTime.parse(getStartDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append(" to ")
                .append(LocalDateTime.parse(getEndDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER));
        return builder.toString();
    }
}
