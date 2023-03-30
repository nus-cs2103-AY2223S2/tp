package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModuleTracker;
import seedu.address.model.ReadOnlyModuleTracker;
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
 * Contains utility methods for populating {@code ModuleTracker} with sample data.
 */
public class SampleDataUtil {
    public static final String EMPTY_INPUT = "None.";
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Name("CS4243"), new Resource("www.google.com"),
                new TimeSlot("Monday 11:00 15:00"), new Address("LT19"),
                getTagSet("Lecture"), new Remark("level-4000 module"),
                new Deadline("230323 15:00"), new Teacher("Currently none.")),
            new Module(new Name("CS4243"), new Resource("https://canvas.nus.edu.sg/"),
                new TimeSlot("Tuesday 13:00 15:00"), new Address("COM 1-B12"),
                getTagSet("Lab"), new Remark("No need to attend"),
                new Deadline("230323 09:00"), new Teacher("Currently none.")),
            new Module(new Name("CS1231S"), new Resource("https://www.comp.nus.edu.sg/~cs1231s/"),
                new TimeSlot(EMPTY_INPUT), new Address("COM1 B1"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("230323 14:00"), new Teacher("Currently none.")),
            new Module(new Name("ST2334"), new Resource("www.youtube.com"),
                new TimeSlot("Thursday 13:00 15:00"), new Address("LT27"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("230323 19:00"), new Teacher("Currently none.")),
            new Module(new Name("CS3230"), new Resource("https://www.khanacademy.org/"),
                new TimeSlot("Friday 13:00 15:00"), new Address("COM3"),
                getTagSet("Tutorial"), new Remark("Currently none."),
                new Deadline("230323 16:00"), new Teacher("Currently none.")),
            new Module(new Name("GEA1000"), new Resource("https://www.microsoft.com/en-us/microsoft-365/excel"),
                new TimeSlot("Saturday 13:00 15:00"), new Address("Online Learning :)"),
                getTagSet("Lecture"), new Remark("Currently none."),
                new Deadline("230323 15:00"), new Teacher("Currently none.")),
            new Module(new Name("MA2001"), new Resource("britannica.com/science/linear-algebra"),
                new TimeSlot("Sunday 13:00 15:00"), new Address("Online Learning :)"),
                getTagSet("Lecture"), new Remark("Very interesting module."),
                new Deadline(EMPTY_INPUT), new Teacher("Currently none."))
        };
    }

    public static ReadOnlyModuleTracker getSampleModuleTracker() {
        ModuleTracker sampleMt = new ModuleTracker();
        for (Module sampleModule : getSampleModules()) {
            sampleMt.addModule(sampleModule);
        }
        return sampleMt;
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
