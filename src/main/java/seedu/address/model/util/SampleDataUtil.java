package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Performance;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Photo;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark();

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("e6611228@u.nus.edu"),
                new Photo("/images/studentProfiles/student_1.png"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                new Performance("30"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("e7654321@u.nus.edu"),
                new Photo("/images/studentProfiles/student_2.png"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                new Performance("81"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("e1122334@u.nus.edu"),
                new Photo("/images/studentProfiles/student_3.png"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                new Performance("4"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("e9988776@u.nus.edu"),
                new Photo("/images/studentProfiles/student_4.png"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                new Performance("19"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("e9999888@u.nus.edu"),
                new Photo("/images/studentProfiles/student_5.png"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                new Performance("89"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("e5555111@u.nus.edu"),
                new Photo("/images/studentProfiles/student_6.png"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                new Performance("50"),
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
