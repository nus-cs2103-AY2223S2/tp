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
import seedu.address.model.module.Resource;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Name("CS4243"), new Resource("www.google.com"),
                new TimeSlot("230223 13:00"), new Address("LT19"),
                getTagSet("Lecture"), new Remark("level-4000 module"),
                new Deadline("230323 13:00"), new Teacher("Currently none.")),
            new Module(new Name("CS4243"), new Resource("https://canvas.nus.edu.sg/"),
                new TimeSlot("230323 14:00"), new Address("COM 1-B12"),
                getTagSet("Lab"), new Remark("No need to attend"),
                new Deadline("230323 13:00"), new Teacher("Currently none.")),
            new Module(new Name("CS1231S"), new Resource("https://www.comp.nus.edu.sg/~cs1231s/"),
                new TimeSlot("230323 15:00"), new Address("COM1 B1"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("230323 13:00"), new Teacher("Currently none.")),
            new Module(new Name("ST2334"), new Resource("www.youtube.com"),
                new TimeSlot("230323 16:00"), new Address("LT27"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("230323 13:00"), new Teacher("Currently none.")),
            new Module(new Name("CS3230"), new Resource("https://www.khanacademy.org/"),
                new TimeSlot("230323 13:00"), new Address("COM3"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("230323 13:00"), new Teacher("Currently none.")),
            new Module(new Name("GEA1000"), new Resource("https://www.microsoft.com/en-us/microsoft-365/excel"),
                new TimeSlot("230323 19:00"), new Address("Online Learning :)"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("230323 13:00"), new Teacher("Currently none."))
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
