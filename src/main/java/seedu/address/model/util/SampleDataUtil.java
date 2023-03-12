package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("CS4243"), new Type("Lecture"), new TimeSlot("Tuesday 2-4 pm"),
                new Address("LT19"),
                getTagSet("Tuesday"), new Remark("Remark: level-4000 module")),
            new Person(new Name("CS4243"), new Type("Lab"), new TimeSlot("Tuesday 12-2 pm"),
                new Address("COM 1-B12"),
                getTagSet("Tuesday"), new Remark("Remark: no need to attend")),
            new Person(new Name("Charlotte Oliveiro"), new Type("93210283"), new TimeSlot("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Remark("this is a remark")),
            new Person(new Name("David Li"), new Type("91031282"), new TimeSlot("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Remark("this is a remark")),
            new Person(new Name("Irfan Ibrahim"), new Type("92492021"), new TimeSlot("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Remark("this is a remark")),
            new Person(new Name("Roy Balakrishnan"), new Type("92624417"), new TimeSlot("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Remark("this is a remark"))
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
