package seedu.dengue.model.person;

import java.time.LocalDate;
/**
 * Comparator for the class {@Date}
 */
public class DateComparator extends GeneralComparator<Date> {
    public int compare(Date d1, Date d2) {
        LocalDate first = LocalDate.parse(d1.value);
        LocalDate second = LocalDate.parse(d2.value);
        if (first.isBefore(second)) {
            return -1;
        } else if (first.isEqual(second)) {
            return 0;
        } else {
            return 1;
        }
    }

}
