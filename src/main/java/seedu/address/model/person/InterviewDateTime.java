package seedu.address.model.person;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents a Person's interview date and time in the address book.
 * Guarantees: interview's date and time are valid
 */
public class InterviewDateTime {
    public static final String MESSAGE_CONSTRAINTS = "Interview date time not in valid format!";

    private final LocalDateTime dateTime;

    public InterviewDateTime(String dateTime) throws ParseException {
        this.dateTime = DateTimeParser.parseDateTime(dateTime);
    }

    public static InterviewDateTime createInterviewDateTime(String dateTime) throws ParseException {
        if (dateTime.equals("")) {
            return null;
        } else {
            return new InterviewDateTime(dateTime);
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static boolean isValidDateTime(String dateTime) {
        try {
            DateTimeParser.parseDateTime(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return DateTimeParser.datetimeFormatter(dateTime);
    }
}
