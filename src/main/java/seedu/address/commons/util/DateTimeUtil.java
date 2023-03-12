package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods to parse LocalDateTime to String, vice versa.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime toDateTime(String s) {
        return LocalDateTime.parse(s, DATE_TIME_FORMATTER);
    }

    public static String dateTimeToString(LocalDateTime t) {
        return t.format(DATE_TIME_FORMATTER);
    }
}
