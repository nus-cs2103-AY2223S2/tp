package seedu.dengue.model.range;

import java.time.LocalDate;
import seedu.dengue.model.person.Date;

/**
 * Comparator for the class {@Date}
 */
public class DateComparator extends GeneralComparator<Date> {
    /**
     * Compares two {@Date} objects.
     * @param d1 the first object to be compared.
     * @param d2 the second object to be compared.
     * @return An integer, which is positive if d2 > d1, zero if d1 == d2 and negative if d1 < d2.
     */
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
