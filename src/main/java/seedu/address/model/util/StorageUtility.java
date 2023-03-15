package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Utility class which contains helper functions related to various commands, such as conversion of date and times
 */
public class StorageUtility {

    /**
     * Parses a date string in the format of "yyyy-MM-dd" from JSON to a LocalDate object.
     * @param dateString the date string to parse
     * @return a LocalDate object representing the parsed date
     */
    public static LocalDate parseDateFromJson(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}

