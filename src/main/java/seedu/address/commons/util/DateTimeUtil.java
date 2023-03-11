package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime toDateTime(String s) {
        return LocalDateTime.parse(s, DATE_TIME_FORMATTER);
    }

    public static String dateTimeToString(LocalDateTime t) {
        return t.format(DATE_TIME_FORMATTER);
    }
}
