package seedu.address.model.event;

import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

/**
 * Creates a Recurring Event for the Calendar.
 */
public class RecurringEvent extends Event {

    public RecurringEvent(Description description, DateTime startDateTime,
                          DateTime endDateTime, Recurrence recurrence) {
        super(description, startDateTime, endDateTime, recurrence);
    }
}
