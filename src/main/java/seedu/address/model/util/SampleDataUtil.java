package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.DeliveryJobSystem;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static DeliveryJob[] getSampleDeliveryJob() {
        Person[] persons = getSamplePersons();
        return new DeliveryJob[] {
            new DeliveryJob(
                persons[0].getPersonId(),
                persons[1].getPersonId(),
                "dummy slot",
                "0.0"),
            new DeliveryJob(
                persons[1].getPersonId(),
                persons[2].getPersonId(),
                "dummy slot",
                "1.0"),
            new DeliveryJob(
                persons[2].getPersonId(),
                persons[3].getPersonId(),
                "dummy slot",
                "2.0"),
            new DeliveryJob(
                persons[3].getPersonId(),
                persons[4].getPersonId(),
                "dummy slot",
                "3.0"),
            new DeliveryJob(
                persons[4].getPersonId(),
                persons[5].getPersonId(),
                "dummy slot",
                "4.0"),
            new DeliveryJob(
                persons[5].getPersonId(),
                persons[0].getPersonId(),
                "dummy slot",
                "5.0")
            };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyDeliveryJobSystem getSampleDeliveryJobSystem() {
        DeliveryJobSystem sampleDS = new DeliveryJobSystem();
        for (DeliveryJob sampleJob : getSampleDeliveryJob()) {
            sampleDS.addDeliveryJob(sampleJob);
        }
        return sampleDS;
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
