package seedu.address.model.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDateTime;

/**
 * Encapsulates useful date time operations in Fish Ahoy!.
 */
public class DateUtil {
    /**
     * Format of dates in Fish Ahoy!.
     */
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    /**
     * Format of dates in Task descriptions.
     */
    public static final String VALID_NAME_FORMAT_DATETIME = "dd-LLL-yyyy HH:mm";

    /**
     * Parses a string date  format in {@code DATE_TIME_FORMAT}
     * @param date Date in {@code String} form
     * @return {@code LocalDateTime} instance from given String
     */
    public static LocalDateTime parseStringToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime;
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

    /**
     * Checks if a Fish needs to be Fed based on lastFedDate and FeedingInterval
     * @param lfdt LastFedDate of FIsh
     * @param fi FeedingInterval of Fish
     * @return true if fish needs to be fed
     */
    public static boolean checkFishNeedsToBeFed(LastFedDateTime lfdt, FeedingInterval fi) {
        LocalDateTime lfdLocalDateTime = lfdt.localDateTime;
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        LocalDateTime feedingDateTime = lfdLocalDateTime.plusDays(Integer.valueOf(fi.days))
                .plusHours(Integer.valueOf(fi.hours));
        return feedingDateTime.isEqual(nowLocalDateTime) || feedingDateTime.isBefore(nowLocalDateTime);
    }

    /**
     * Converts LocalDateTime to a string using DATE_TIME_FORMAT
     * @param dateTime local date time
     * @return string format
     */
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        String formatted = dateTime.format(formatter);
        return formatted;
    }

    /**
     * Returns the current date time
     * @return current date time
     */
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        String formattedNow = now.format(formatter);
        return formattedNow;
    }

    /**
     * Returns a String that is a valid Task description
     * @param dateTime LocalDateTime to be formated
     * @return Formatted string that is alphanumeric
     */
    public static String getTaskDescriptionDateTimeFormat(LocalDateTime dateTime) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(VALID_NAME_FORMAT_DATETIME);
        return dateTime.format(outputFormat);
    }
}
