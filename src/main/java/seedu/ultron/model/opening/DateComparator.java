package seedu.ultron.model.opening;

import java.time.LocalDate;
import java.util.Comparator;

public class DateComparator implements Comparator<Date> {

    @Override
    public int compare(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return 0;
        } else if (date1 == null) {
            return -1;
        } else if (date2 == null) {
            return 1;
        } else {
            LocalDate date1LocalDate = LocalDate.parse(date1.fullDate);
            LocalDate date2LocalDate = LocalDate.parse(date2.fullDate);
            return date1LocalDate.compareTo(date2LocalDate);
        }
    }
}
