package seedu.event.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.event.model.ContactList;
import seedu.event.model.EventBook;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.contact.Contact;
import seedu.event.model.contact.ContactName;
import seedu.event.model.contact.ContactPhone;
import seedu.event.model.event.Address;
import seedu.event.model.event.Event;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EventBook} with sample data.
 */
public class SampleDataUtil {

    public static Contact[] getSampleContacts() {
        return new Contact [] {
            new Contact(new ContactName("Gladious Lee"), new ContactPhone("92348274")),
            new Contact(new ContactName("Sofia Neistat"), new ContactPhone("81274623")),
            new Contact(new ContactName("Tan Pei Ming"), new ContactPhone("91120392"))
        };
    }
    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Wedding Lunch Photography"), new Rate("120"),
                new Address("York Hotel ballroom"),
                new Time("05-05-2023 11:00"), new Time("05-05-2023 14:30"),
                getTagSet("carousell")),
            new Event(new Name("Wedding Dinner Photobooth"), new Rate("80"),
                new Address("3 rich avenue SG 991203"),
                new Time("08-05-2023 19:00"), new Time("08-05-2023 22:30"),
                getTagSet("photobooth", "carousell")),
            new Event(new Name("Barista at Chanel launch"), new Rate("100"),
                new Address("Open area @ Orchard Road"),
                new Time("02-05-2023 11:00"), new Time("02-05-2023 18:00"),
                getTagSet("barista")),
            new Event(new Name("Wedding Lunch Live Singing"), new Rate("280"),
                new Address("Raffles Hotel Jubilee Ballroom"),
                new Time("15-05-2023 11:00"), new Time("15-05-2023 14:00"),
                getTagSet("family"))
        };
    }

    public static ReadOnlyEventBook getSampleEventBook() {
        EventBook sampleAb = new EventBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleCl = new ContactList();
        for (Contact sampleContact : getSampleContacts()) {
            sampleCl.addContact(sampleContact);
        }
        return sampleCl;
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
