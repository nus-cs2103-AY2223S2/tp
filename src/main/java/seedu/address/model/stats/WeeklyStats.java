package seedu.address.model.stats;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeeklyStats {
    private List<LocalDate> dates;

    public WeeklyStats(LocalDate date) {
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
