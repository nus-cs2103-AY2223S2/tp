package seedu.internship.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling LocalDateTime
 */
public class DateTimeUtil {

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
