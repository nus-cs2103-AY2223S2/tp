package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's meetings in the address book.
 */
public class Meeting {
    public static final String MEETING_CONSTRAINTS = "Start time should be before end time!";

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Meeting
     * @param start start date and time of meeting
     * @param end end date and time of meeting. Must be after start
     */
    public Meeting(LocalDateTime start, LocalDateTime end) throws ParseException {
        if (end.isBefore(start)) {
            throw new ParseException(MEETING_CONSTRAINTS);
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Default Constructor that generates an empty meeting
     */
    public Meeting() {
        this.start = LocalDateTime.MIN;
        this.end = LocalDateTime.MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Meeting)) {
            return false;
        }
        Meeting mt = (Meeting) o;
        return mt.start.equals(this.start) && mt.end == this.end;
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("eeee MMMM d u HH:mm");
        String startDnT = start.format(customFormat);
        String endDnT = end.format(customFormat);

        return "(Starts at: " + startDnT + " Ends at: " + endDnT + ")";
    }

    public boolean isEmpty() {
        return this.start.equals(LocalDateTime.MIN)
                && this.end.equals(LocalDateTime.MIN);
    }
}
