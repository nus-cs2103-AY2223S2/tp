package seedu.address.model.event;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.enums.Interval;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

/**
 * Creates a One Time Event for Calendar.
 */
public class OneTimeEvent extends Event {

    public OneTimeEvent(Description description, DateTime startDateTime,
                        DateTime endDateTime, Set<Person> taggedPeople) {
        super(description, startDateTime, endDateTime, new Recurrence(Interval.NONE), taggedPeople);
    }

    @Override
    public Event copy() {
        return new OneTimeEvent(
                new Description(description.toString()),
                new DateTime(startDateTime.toString()),
                new DateTime(endDateTime.toString()),
                new HashSet<>(taggedPeople)
        );
    }
}
