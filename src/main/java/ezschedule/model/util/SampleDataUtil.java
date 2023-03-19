package ezschedule.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import ezschedule.model.AddressBook;
import ezschedule.model.ReadOnlyAddressBook;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Date;
import ezschedule.model.event.Time;
import ezschedule.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Event[] getSampleEvents() {
        return new Event[]{
            new Event(new Name("Alex Yeoh"), new Date("01-02-2024"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Bernice Yu"), new Date("01-02-2024"), new Time("18:00"), new Time("20:00")),
            new Event(new Name("Charlotte Olivero"), new Date("01-02-2024"), new Time("18:00"), new Time("20:00"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
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
