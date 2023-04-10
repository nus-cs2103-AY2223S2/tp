package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InterviewDateTime;


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
            throw new ParseException(InterviewDateTime.MESSAGE_CONSTRAINTS);
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

    /**
     * Returns if a given dateTime string is valid
     * Format: DD-MM-YYYY HH:MM
     * @param dateTime String input for datetime.
     * @return boolean value representing if the input is valid.
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            DateTimeParser.parseDateTime(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
