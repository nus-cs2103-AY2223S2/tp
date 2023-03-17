package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.event.Address;
import seedu.address.model.event.Event;
import seedu.address.model.event.Name;
import seedu.address.model.event.Rate;
import seedu.address.model.event.Time;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Contact[] getSampleContacts() {
        return new Contact [] {
            new Contact(new ContactName("Deborah Tan"), new ContactPhone("91234567")),
            new Contact(new ContactName("Mandy"), new ContactPhone("98765432"))
        };
    }
    public static Event[] getSamplePersons() {
        return new Event[] {
            new Event(new Name("Event 1"), new Rate("1"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Time("02-02-2023 11:00"), new Time("02-02-2023 11:30"),
                getTagSet("friends")),
            new Event(new Name("Event 2"), new Rate("2"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Time("02-04-2023 06:00"), new Time("02-04-2023 11:30"),
                getTagSet("colleagues", "friends")),
            new Event(new Name("Event 3"), new Rate("3"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Time("02-02-2023 11:00"), new Time("02-02-2023 11:30"),
                getTagSet("neighbours")),
            new Event(new Name("Event 4"), new Rate("4"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Time("02-02-2023 11:00"), new Time("02-02-2023 11:30"),
                getTagSet("family")),
            new Event(new Name("Event 5"), new Rate("5"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new Time("02-02-2023 11:00"), new Time("02-02-2023 11:30"),
                getTagSet("classmates")),
            new Event(new Name("Event 6"), new Rate("6"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Time("02-02-2023 11:00"), new Time("02-02-2023 11:30"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Event samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
