package seedu.dengue.model.range;

import java.time.LocalDate;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the start of the date in a given range
 */
public class StartDate extends Date implements Start<Date> {

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public StartDate(String date) {
        super(date);
    }

    /**
     * Checks for whether the end value of the age range is before the date of the person.
     *
     * @param p
     */
    public boolean isBefore(Person p) {
        LocalDate d1 = LocalDate.parse(value);
        LocalDate d2 = LocalDate.parse(p.getDate().value);
        return d1.compareTo(d2) <= 0;
    }
}
