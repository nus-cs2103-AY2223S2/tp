package seedu.dengue.logic.comparators;

import java.time.LocalDate;
import java.util.Comparator;

import seedu.dengue.model.person.Date;

/**
 * Comparator for the class {@code Date}
 */
public class DateComparator implements Comparator<Date> {
    /**
     * Compares two {@code Date} objects.
     * @param d1 the first object to be compared.
     * @param d2 the second object to be compared.
     * @return An integer which is positive if d2 is after d1, zero if d1 is equal to d2 and negative otherwise.
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
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

}
