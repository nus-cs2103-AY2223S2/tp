package expresslibrary.commons.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * A class for accessing the Date File.
 */
public final class DateUtil {
    private static FastDateFormat dateFormatter = FastDateFormat.getInstance("dd/MM/yyyy");
    private DateUtil() {
        throw new IllegalStateException("DateUtil class");
    }

    public static String formatDate(Date date) {
        return dateFormatter.format(date);
    }

    public static Date parseDate(String dateString) throws ParseException {
        return dateFormatter.parse(dateString);
    }
}
