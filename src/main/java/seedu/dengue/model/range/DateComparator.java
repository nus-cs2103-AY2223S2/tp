package seedu.dengue.model.range;

import java.time.LocalDate;

import seedu.dengue.model.person.Date;

/**
 * Comparator for the class {@code Date}
 */
public class DateComparator extends GeneralComparator<Date> {
    /**
     * Compares two {@code Date} objects.
     * @param d1 the first object to be compared.
     * @param d2 the second object to be compared.
     * @return A negative integer, zero, or a positive integer if the first argument is
     *         less than, equal to, or greater than the second.
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
