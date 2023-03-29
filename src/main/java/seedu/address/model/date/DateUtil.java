package seedu.address.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDate;

/**
 * Encapsulates useful date time operations in Fish Ahoy!.
 */
public class DateUtil {
    /**
     * Format of dates in Fish Ahoy!.
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Format of dates in Task descriptions.
     */
    public static final String VALID_NAME_FORMAT = "dd LLL yyyy";

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
     * @param lfd LastFedDate of FIsh
     * @param fi FeedingInterval of Fish
     * @return true if fish needs to be fed
     */
    public static boolean checkFishNeedsToBeFed(LastFedDate lfd, FeedingInterval fi) {
        LocalDate lfdLocalDate = lfd.localDate;
        LocalDate nowLocalDate = LocalDate.now();
        LocalDate feedingDate = lfdLocalDate.plusDays(Integer.valueOf(fi.days));
        return feedingDate.isEqual(nowLocalDate) || feedingDate.isBefore(nowLocalDate);
    }

    /**
     * Returns a String that is a valid Task description
     * @param date LocalDate to be formated
     * @return Formatted string that is alphanumeric
     */
    public static String getTaskDescriptionDateFormat(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(VALID_NAME_FORMAT);
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
}
