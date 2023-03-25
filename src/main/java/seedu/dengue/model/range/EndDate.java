package seedu.dengue.model.range;

import java.time.LocalDate;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

public class EndDate extends Date implements End<Date> {

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public EndDate(String date) {
        super(date);
    }

    public boolean isAfter(Person p) {
        LocalDate d1 = LocalDate.parse(value);
        LocalDate d2 = LocalDate.parse(p.getDate().value);
        return d1.compareTo(d2) >= 0;
    }
}
