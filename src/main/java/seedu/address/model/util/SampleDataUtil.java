package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.Remark;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.module.Type;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Name("CS4243"), new Type("Lecture"), new TimeSlot("Tuesday 2-4 pm"),
                new Address("LT19"),
                getTagSet("Lecture"), new Remark("level-4000 module"),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Module(new Name("CS4243"), new Type("Lab"), new TimeSlot("Tuesday 12-2 pm"),
                new Address("COM 1-B12"),
                getTagSet("Lab"), new Remark("No need to attend"),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Module(new Name("CS1231S"), new Type("Tutorial"), new TimeSlot("4pm - 6pm"),
                new Address("COM1 B1"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Module(new Name("ST2334"), new Type("Lecture"), new TimeSlot("8am-10am"),
                new Address("LT27"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Module(new Name("CS3230"), new Type("Tutorial"), new TimeSlot("4 - 5pm"),
                new Address("COM3"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none.")),
            new Module(new Name("GEA1000"), new Type("Lecture"), new TimeSlot("None"),
                new Address("Online Learning :)"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("Currently none."), new Teacher("Currently none."))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
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
