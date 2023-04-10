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
            new Person("ALESAM", new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("Regular")),
            new Person("BERSAM", new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("Business", "Regular")),
            new Person("CHASAM", new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("New")),
            new Person("DAVSAM", new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("Individual")),
            new Person("IRFSAM", new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("Regular")),
            new Person("ROYSAM", new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("Business"))
        };
    }

    public static DeliveryJob[] getSampleDeliveryJob() {
        Person[] persons = getSamplePersons();
        return new DeliveryJob[] {
            new DeliveryJob(
                persons[0].getPersonId(),
                persons[1].getPersonId(),
                "2023-03-01",
                "1",
                "0.0", ""),
            new DeliveryJob(
                persons[1].getPersonId(),
                persons[2].getPersonId(),
                "2023-03-01",
                "2",
                "1.0", ""),
            new DeliveryJob(
                persons[2].getPersonId(),
                persons[3].getPersonId(),
                "2023-03-01",
                "2",
                "2.0", ""),
            new DeliveryJob(
                persons[3].getPersonId(),
                persons[4].getPersonId(),
                "2023-03-01",
                "3",
                "3.0", ""),
            new DeliveryJob(
                persons[4].getPersonId(),
                persons[5].getPersonId(),
                "2023-03-02",
                "5",
                "4.0", ""),
            new DeliveryJob(
                persons[5].getPersonId(),
                persons[0].getPersonId(),
                "5.0", "")
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
