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
}
