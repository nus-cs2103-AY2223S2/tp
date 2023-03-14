package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        new PayRate("90"),
                        new Session("28-02-2022 23:59", "01-03-2022 01:00"),
                        getTagSet("friends")),
                new Person(new Name("Bernice Yu"), new Phone("99272758"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new PayRate("123"),
                        new Session("28-02-2022 23:00", "01-03-2022 00:00"),
                        getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new PayRate("12"),
                        new Session("18-02-2022 23:59", "19-02-2022 00:59"),
                        getTagSet("neighbours")),
                new Person(new Name("David Li"), new Phone("91031282"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new PayRate("0"),
                        new Session("18-02-2022 21:59", "19-02-2022 00:00"),
                        getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        new PayRate("5"),
                        new Session("18-02-2022 23:59", "19-02-2022 01:00"),
                        getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        new PayRate("5"),
                        new Session("18-02-2022 23:59", "19-02-2022 01:00"),
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
