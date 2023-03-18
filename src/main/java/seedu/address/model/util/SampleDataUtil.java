package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyScheduler;
import seedu.address.model.Scheduler;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.Name;
import seedu.address.model.event.Time;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Scheduler} with sample data.
 */
public class SampleDataUtil {

    public static Event[] getSampleEvents() {
        return new Event[]{
            new Event(new Name("Tennis"), new Date("01-01-2024"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Basketball"), new Date("02-02-2024"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Bowling"), new Date("03-03-2024"), new Time("18:00"), new Time("20:00"))
        };
    }

    public static ReadOnlyScheduler getSampleScheduler() {
        Scheduler sampleSc = new Scheduler();
        for (Event sampleEvent : getSampleEvents()) {
            sampleSc.addEvent(sampleEvent);
        }
        return sampleSc;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }
}
