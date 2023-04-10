package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTuteeManagingSystem;
import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.fields.Address;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Email;
import seedu.address.model.tutee.fields.EndTime;
import seedu.address.model.tutee.fields.Lesson;
import seedu.address.model.tutee.fields.Name;
import seedu.address.model.tutee.fields.Phone;
import seedu.address.model.tutee.fields.Remark;
import seedu.address.model.tutee.fields.Schedule;
import seedu.address.model.tutee.fields.StartTime;
import seedu.address.model.tutee.fields.Subject;

/**
 * Contains utility methods for populating {@code TuteeManagingSystem} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Tutee[] getSamplePersons() {
        return new Tutee[] {
            new Tutee(
                new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("monday"),
                new StartTime("08:30"),
                new EndTime("09:30"),
                getTagSet("GoodEffort"),
                new Lesson()
            ),
            new Tutee(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("monday"),
                new StartTime("08:30"),
                new EndTime("11:30"),
                getTagSet("GoodEffort"),
                new Lesson()
            ),
            new Tutee(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("friday"),
                new StartTime("08:00"),
                new EndTime("10:30"),
                getTagSet("neighbours"),
                new Lesson()
            ),
            new Tutee(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("thursday"),
                new StartTime("08:00"),
                new EndTime("09:30"),
                getTagSet("family"),
                new Lesson()
            ),
            new Tutee(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("wednesday"),
                new StartTime("09:30"),
                new EndTime("11:30"),
                getTagSet("classmates"),
                new Lesson()
            ),
            new Tutee(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Attendance(),
                EMPTY_REMARK,
                new Subject("Math"),
                new Schedule("tuesday"),
                new StartTime("10:30"),
                new EndTime("12:30"),
                getTagSet("colleagues"),
                new Lesson())
        };
    }

    public static ReadOnlyTuteeManagingSystem getSampleAddressBook() {
        TuteeManagingSystem sampleAb = new TuteeManagingSystem();
        for (Tutee sampleTutee : getSamplePersons()) {
            sampleAb.addPerson(sampleTutee);
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
