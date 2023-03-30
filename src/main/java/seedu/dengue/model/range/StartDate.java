package seedu.dengue.model.range;

import java.time.LocalDate;
import java.util.Optional;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the start of the date in a given range
 */
public class StartDate implements Start<Date> {

    public final Optional<Date> date;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public StartDate(Optional<Date> date) {
        this.date = date;
    }

    /**
     * Checks for whether the start value of the date range is before the date of the person.
     *
     * @param p A Person.
     */
    public boolean isBefore(Person p) {
        if (!date.isPresent()) {
            return true;
        }
        LocalDate d1 = LocalDate.parse(date.get().value);
        LocalDate d2 = LocalDate.parse(p.getDate().value);
        return d1.compareTo(d2) <= 0;
    }

    /**
     * Checks for whether the start value of the date range is before the given end date.
     *
     * @param end An EndDate.
     */
    public boolean isBefore(EndDate end) {
        if (!date.isPresent()) {
            return true;
        }
        LocalDate d1 = LocalDate.parse(date.get().value);
        LocalDate d2 = LocalDate.parse(end.date.get().value);
        return d1.compareTo(d2) <= 0;
    }

    public Date get() {
        return date.get();
    }
}
