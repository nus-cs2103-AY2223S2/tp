package seedu.address.model.person;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents a Person's interview date and time in the address book.
 */
public class InterviewDateTime {
    private final LocalDateTime dateTime;
    public static final String EMPTY_DATE_TIME = "";
    public InterviewDateTime(String dateTime) throws ParseException {
        this.dateTime = DateTimeParser.parseDateTime(dateTime);
    }
    @Override
    public String toString() {
        return DateTimeParser.datetimeFormatter(dateTime);
    }
}
