package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents statistics to be displayed for a given week.
 */
public class WeeklyStats {
    private List<LocalDate> dates;

    /**
     * Constructor of the WeeklyStats object.
     * @param date Week which date is in. Cannot be negative and cannot be null.
     */
    public WeeklyStats(LocalDate date) {
        requireNonNull(date);
        this.dates = getWeekDates(date);
    }

    public List<LocalDate> getWeekDates(LocalDate date) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate startOfWeek = date.with(DayOfWeek.MONDAY);
        for (int i = 0; i < 7; i++) {
            dates.add(startOfWeek.plusDays(i));
        }
        return dates;
    }

    public List<LocalDate> getDates() {
        return this.dates;
    }
}
