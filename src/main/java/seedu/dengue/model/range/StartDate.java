package seedu.dengue.model.range;

import seedu.dengue.model.person.Date;

public class StartDate extends Date implements Start<Date> {
    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public StartDate(String date) {
        super(date);
    }
}
