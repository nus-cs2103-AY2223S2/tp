package seedu.internship.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling LocalDateTime
 */
public class DateTimeUtil {

    /**
     * Checks if a given String is in the correct Date Time format
     *
     * @param s Input String.
     * @param dateTimeFormatter Format of Date Time.
     * @return True if the String is in correct format.
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
