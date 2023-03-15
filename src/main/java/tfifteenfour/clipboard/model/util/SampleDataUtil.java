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
                        new StudentId("A1237890J"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
                new Student(new Name("Emily Tan"), new Phone("92223344"), new Email("emilytan@example.com"),
                        new StudentId("A4567890K"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("George Lim"), new Phone("93334455"), new Email("georgelim@example.com"),
                        new StudentId("A5678901L"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Hannah Ng"), new Phone("94445566"), new Email("hannahng@example.com"),
                        new StudentId("A6789012M"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Jackie Lee"), new Phone("95556677"), new Email("jackielee@example.com"),
                        new StudentId("A7890123N"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Katie Wong"), new Phone("96667788"), new Email("katiewong@example.com"),
                        new StudentId("A8901234O"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Liam Tan"), new Phone("97778899"), new Email("liamtan@example.com"),
                        new StudentId("A9012345P"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Maggie Tan"), new Phone("98889900"), new Email("maggietan@example.com"),
                        new StudentId("A0123456Q"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Nathan Lee"), new Phone("90001122"), new Email("nathanlee@example.com"),
                        new StudentId("A1234567R"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Priya Kumar"), new Phone("91112233"), new Email("priyakumar@example.com"),
                        new StudentId("A2345678S"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team3")),
                new Student(new Name("Rahul Gupta"), new Phone("92223344"), new Email("rahulgupta@example.com"),
                        new StudentId("A3456789T"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team3")),
                new Student(new Name("Siti Binte Ahmad"), new Phone("93334455"), new Email("sitiahmad@example.com"),
                        new StudentId("A4567890U"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team3")),
                new Student(new Name("Hafiz Bin Mohamed"), new Phone("94445566"), new Email("hafizmohamed@example.com"),
                        new StudentId("A5678901V"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team3")),
                new Student(new Name("Tan Yee Hui"), new Phone("94561238"), new Email("yeehuitan@example.com"),
                        new StudentId("A7890123X"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Wong Chun Kit"), new Phone("96512347"), new Email("chunkitwong@example.com"),
                        new StudentId("A8901234Y"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Yap Shu Yun"), new Phone("91237845"), new Email("shuyunyap@example.com"),
                        new StudentId("A9012345Z"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Chua Hong Wei"), new Phone("98273461"), new Email("hongweichua@example.com"),
                        new StudentId("A0123456A"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Lim Yi Ling"), new Phone("97536128"), new Email("yilinglim@example.com"),
                        new StudentId("A1234567B"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team2")),
                new Student(new Name("Muhammad Azmi Bin Hamzah"), new Phone("91234567"), new Email("azmi.hamzah@example.com"),
                        new StudentId("A2345678C"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1")),
                new Student(new Name("Nurul Izzah Binte Yusoff"), new Phone("94321876"), new Email("nurul.izzah@example.com"),
                        new StudentId("A3456789D"), getModuleSet("CS2105"), EMPTY_REMARK, getTagSet("Team1"))
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
