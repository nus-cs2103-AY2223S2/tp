package seedu.dengue.model.range;

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
        return DATE_COMPARATOR.compare(this, p.getDate()) >= 0;
    }
}
