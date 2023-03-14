package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new LocalDateTime object
 */
public class DateTimeParser {
    /**
     * Static method to format datetime string into a LocalDateTime object.
     * @param dateTimeString String input for datetime.
     * @return LocalDateTime object representing the current datetime.
     * @throws ParseException If the input is not in the write format to be converted.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws ParseException {
        // Format DD-MM-YYYY HH:MM
        if (dateTimeString.length() == 0) {
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            return LocalDateTime.parse(dateTimeString, format);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATETIME);
        }
    }

    /**
     * Takes in the dateTime object to produce the right format to show to the User.
     * @param datetime LocalDateTime object
     * @return String output of the datetime.
     */
    public static String datetimeFormatter(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
