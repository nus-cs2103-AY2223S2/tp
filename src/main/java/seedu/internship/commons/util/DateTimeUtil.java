package seedu.internship.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling LocalDateTime.
 */
public class DateTimeUtil {

    /**
     * Returns true if the given string is in the appropriate format for parsing into a LocalDateTime
     * object by the given DateTimeFormatter.
     * @param s String to be parsed.
     * @param dateTimeFormatter Formatter to parse the string by.
     * @return True/False if the string can be parsed into LocalDateTime.
     */
    public static boolean isValidLocalDateTimeString(String s, DateTimeFormatter dateTimeFormatter) {
        requireNonNull(s);
        requireNonNull(dateTimeFormatter);

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }
}
