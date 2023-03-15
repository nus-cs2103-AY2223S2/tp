package seedu.address.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates useful date time operations in Fish Ahoy!.
 */
public class DateUtil {
    /**
     * Format of dates in Fish Ahoy!.
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Parses a string date  format in {@code DATE_FORMAT}
     * @param date Date in {@code String} form
     * @return {@code LocalDate} instance from given String
     */
    public static LocalDate parseStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    /**
     * Parses a feeding interval in the format \days\d\hours\h to return the days d
     * @param feedingInterval a feeding interval in the format \days\d\hours\h
     * @return number of days d
     */
    public static int parseFeedingIntervalDays(String feedingInterval) {
        int dIndex = feedingInterval.indexOf('d');
        String daysString = feedingInterval.substring(0, dIndex);
        return Integer.parseInt(daysString);
    }

    /**
     * Parses a feeding interval in the format \days\d\hours\h to return the hours h
     * @param feedingInterval a feeding interval in the format \days\d\hours\h
     * @return number of hours h
     */
    public static int parseFeedingIntervalHours(String feedingInterval) {
        int dIndex = feedingInterval.indexOf('d');
        int hIndex = feedingInterval.indexOf('h');
        String hoursString = feedingInterval.substring(dIndex + 1, hIndex);
        return Integer.parseInt(hoursString);
    }
}
