package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getEventSet(new Event(new EventName("Taylor Swift: The Eras Tour"), new DateTime("01-05-2023 19:00"),
                        new DateTime("01-05-2023 22:00")))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getEventSet(new Event(new EventName("Disney On Ice presents Mickey and Friends"),
                        new DateTime("11-03-2023 17:00"),
                        new DateTime("11-03-2023 19:00")))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getEventSet()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getEventSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getEventSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getEventSet())
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventName("BLACKPINK World Tour - Born Pink"),
                    new DateTime("13-05-2023 19:30"),
                    new DateTime("13-05-2023 21:30")),
            new Event(new EventName("Disney On Ice presents Mickey and Friends"),
                    new DateTime("11-03-2023 17:00"),
                    new DateTime("11-03-2023 19:00")),
            new Event(new EventName("Louis Tomlinson: Faith In The Future World Tour"),
                    new DateTime("27-04-2023 19:30"),
                    new DateTime("27-04-2023 21:30")),
            new Event(new EventName("Taylor Swift: The Eras Tour"), new DateTime("01-05-2023 19:00"),
                new DateTime("01-05-2023 22:00")),
            new Event(new EventName("Yiruma Live in Singapore 2023"), new DateTime("02-05-2023 20:00"),
                 new DateTime("02-05-2023 21:00"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Event> getEventSet(Event... events) {
        return Arrays.stream(events).collect(Collectors.toSet());
    }

}
