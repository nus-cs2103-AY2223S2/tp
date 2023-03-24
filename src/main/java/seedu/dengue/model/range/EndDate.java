package seedu.dengue.model.range;

import seedu.dengue.model.person.Date;

public class EndDate extends Date implements End<Date> {

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public EndDate(String date) {
        super(date);
    }
}
