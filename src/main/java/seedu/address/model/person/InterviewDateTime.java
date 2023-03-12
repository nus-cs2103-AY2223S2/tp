package seedu.address.model.person;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents a Person's interview date and time in the address book.
 * Guarantees: interview's date and time are valid
 */
public class InterviewDateTime {
    private static LocalDateTime dateTime;
    public InterviewDateTime(String dateTime) throws ParseException {
        this.dateTime = DateTimeParser.parseDateTime(dateTime);
    }
    @Override
    public String toString() {
        return DateTimeParser.datetimeFormatter(dateTime);
    }
}
