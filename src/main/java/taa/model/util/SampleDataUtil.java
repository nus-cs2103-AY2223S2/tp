package taa.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import taa.model.AddressBook;
import taa.model.ReadOnlyAddressBook;
import taa.model.student.Email;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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

}
