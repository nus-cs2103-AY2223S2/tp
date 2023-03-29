package seedu.address.model.date;

import java.time.LocalDate;
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
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    /**
     * Format of dates in Task descriptions.
     */
    public static final String VALID_NAME_FORMAT_DATE = "dd-LLL-yyyy";
    public static final String VALID_NAME_FORMAT_DATETIME = "dd-LLL-yyyy HH:mm";

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
     * Returns a String that is a valid Task description
     * @param date LocalDateTime to be formated
     * @return Formatted string that is alphanumeric
     */
    public static String getTaskDescriptionDateFormat(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(VALID_NAME_FORMAT_DATE);
        return date.format(outputFormat);
    }

    /**
     * Returns the current date
     * @return current date
     */
    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String formattedDate = today.format(formatter);
        return formattedDate;
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
