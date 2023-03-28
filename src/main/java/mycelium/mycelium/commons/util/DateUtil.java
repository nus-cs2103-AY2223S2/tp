package mycelium.mycelium.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;



/**
 * A class for Date utility functions.
 */
public class DateUtil {
    /**
     * Checks if the {@code date} is within this or next week.
     * Week is counted as starting from Sunday.
     *
     * @param date cannot be null
     * @return true if the {@code date} is within this or next week.
     *          false otherwise.
     */
    public static boolean isWithinThisAndNextWeek(LocalDate date) {
        requireNonNull(date);
        assert date != null;
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfCurrentWeek =
                currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfNextWeek =
                startOfCurrentWeek.plusDays(13);

        return (date.isAfter(startOfCurrentWeek) || date.isEqual(startOfCurrentWeek))
                && (date.isEqual(endOfNextWeek) || date.isBefore(endOfNextWeek));
    }

    /**
     * Checks if {@code date} is before current date.
     *
     * @param date cannot be null
     * @return true if {@code date} is before current time.
     *          false otherwise.
     */
    public static boolean isBeforeToday(LocalDate date) {
        requireNonNull(date);
        LocalDate currentDate = LocalDate.now();

        return date.isBefore(currentDate);
    }
}
