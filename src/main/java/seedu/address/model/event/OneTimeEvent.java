package seedu.address.model.event;

import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

/**
 * Creates a One Time Event for Calendar.
 */
public class OneTimeEvent extends Event {

    public OneTimeEvent(Description description, DateTime startDateTime,
                        DateTime endDateTime) {
        super(description, startDateTime, endDateTime, new Recurrence("none"));
    }
}
