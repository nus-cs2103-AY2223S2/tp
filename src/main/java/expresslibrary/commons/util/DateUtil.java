package expresslibrary.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * A class for accessing the Date File.
 */
public final class DateUtil {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private DateUtil() {
        throw new IllegalStateException("DateUtil class");
    }

    public static String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, dateFormatter.withResolverStyle(ResolverStyle.STRICT));
    }
}
