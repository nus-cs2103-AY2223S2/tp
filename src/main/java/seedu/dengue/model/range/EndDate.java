package seedu.dengue.model.range;

import java.time.LocalDate;
import java.util.Optional;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the end of the date in a given range
 */
public class EndDate implements End<Date> {

    private final Optional<Date> date;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public EndDate(Optional<Date> date) {
        this.date = date;
    }

    /**
     * Checks for whether the end value of the date range is after the age of the person.
     *
     * @param p
     */
    public boolean isAfter(Person p) {
        if (!date.isPresent()) {
            return true;
        }
        LocalDate d1 = LocalDate.parse(date.get().value);
        LocalDate d2 = LocalDate.parse(p.getDate().value);
        return d1.compareTo(d2) >= 0;
    }
}
