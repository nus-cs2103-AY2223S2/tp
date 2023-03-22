package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    /**
     * Returns an effective start {@code DateTime} that ensures the end {@code DateTime}
     * is after the present.
     */
    @Override
    public DateTime getEffectiveStartDateTime() {
        long minutesElapsed = DateTime.getIntervalDuration(getStartDateTime(), getEndDateTime(), ChronoUnit.MINUTES);
        DateTime effectiveEnd = getEffectiveEndDateTime();
        return new DateTime(effectiveEnd.getDateTime().minusMinutes(minutesElapsed));
    }

    /**
     * Returns an effective end {@code DateTime} that is after the present.
     */
    @Override
    public DateTime getEffectiveEndDateTime() {
        LocalDateTime current = getEndDateTime().getDateTime();
        while (current.isBefore(LocalDateTime.now())) {
            current = current.plus(1, getRecurrence().getIncrementUnit());
        }
        return new DateTime(current);
    }
}
