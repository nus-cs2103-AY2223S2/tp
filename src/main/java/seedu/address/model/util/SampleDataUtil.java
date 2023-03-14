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
                getTagSet("Tuesday"), new Remark("level-4000 module"),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Person(new Name("CS4243"), new Type("Lab"), new TimeSlot("Tuesday 12-2 pm"),
                new Address("COM 1-B12"),
                getTagSet("Tuesday"), new Remark("No need to attend"),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Person(new Name("CS1231S"), new Type("Tutorial"), new TimeSlot("4pm - 6pm"),
                new Address("COM1 B1"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Person(new Name("ST2334"), new Type("Lecture"), new TimeSlot("8am-10am"),
                new Address("LT27"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Person(new Name("CS3230"), new Type("Tutorial"), new TimeSlot("4 - 5pm"),
                new Address("COM3"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Person(new Name("GEA1000"), new Type("Lecture"), new TimeSlot("None"),
                new Address("Online Learning :)"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none."))
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
