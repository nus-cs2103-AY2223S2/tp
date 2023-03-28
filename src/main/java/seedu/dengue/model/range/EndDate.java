package seedu.dengue.model.range;

import java.time.LocalDate;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the end of the date in a given range
 */
public class EndDate extends Date implements End<Date> {

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public EndDate(String date) {
        super(date);
    }

    /**
     * Checks for whether the end value of the date range is after the age of the person.
     *
     * @param p
     */
    public boolean isAfter(Person p) {
        LocalDate d1 = LocalDate.parse(value);
        LocalDate d2 = LocalDate.parse(p.getDate().value);
        return d1.compareTo(d2) >= 0;
    }
}
