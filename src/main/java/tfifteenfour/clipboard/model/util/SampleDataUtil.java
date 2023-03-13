package tfifteenfour.clipboard.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.ModuleCode;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Roster} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new StudentId("A0123456X"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new StudentId("A9876543F"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new StudentId("A7654321P"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new StudentId("A8901234H"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new StudentId("A0987654G"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new StudentId("A1237890J"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1"))
        };
    }

    public static ReadOnlyRoster getSampleRoster() {
        Roster sampleAb = new Roster();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
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
    /**
     * Returns a module set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleCode::new)
                .collect(Collectors.toSet());
    }
}
