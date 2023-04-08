package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

/**
 * Creates a Recurring Event for the Calendar.
 */
public class RecurringEvent extends Event {

    public RecurringEvent(Description description, DateTime startDateTime,
                          DateTime endDateTime, Recurrence recurrence, Set<Person> taggedPeople) {
        super(description, startDateTime, endDateTime, recurrence, taggedPeople);
    }

    /**
     * Returns an effective start {@code DateTime} that ensures the end {@code DateTime}
     * is after the present.
     */
    @Override
    public DateTime getEffectiveStartDateTime() {
        if (!this.isRecurring()) {
            return new DateTime(getStartDateTime().getDateTime());
        }
        // Gets the difference between start date time, end date time.
        long minutesElapsed = DateTime.getIntervalDuration(getStartDateTime(), getEndDateTime(), ChronoUnit.MINUTES);
        // Then calculates the effective end datetime.
        DateTime effectiveEnd = getEffectiveEndDateTime();
        // Then we get the new start date time by doing end date time minus the difference calculated earlier.
        return new DateTime(effectiveEnd.getDateTime().minusMinutes(minutesElapsed));
    }

    /**
     * Returns an effective end {@code DateTime} that is after the present.
     */
    @Override
    public DateTime getEffectiveEndDateTime() {
        LocalDateTime current = getEndDateTime().getDateTime();
        if (!this.isRecurring()) {
            return new DateTime(current);
        }
        while (current.isBefore(LocalDateTime.now())) {
            current = current.plus(1, getRecurrence().getIncrementUnit());
        }
        return new DateTime(current);
    }

    @Override
    public Event copy() {
        return new RecurringEvent(
                new Description(description.toString()),
                new DateTime(startDateTime.toString()),
                new DateTime(endDateTime.toString()),
                new Recurrence(recurrence.interval),
                new HashSet<>(taggedPeople)
        );
    }
}
