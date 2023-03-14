package taa.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import taa.model.ClassList;
import taa.model.ReadOnlyAddressBook;
import taa.model.Tutor;
import taa.model.UniqueClassLists;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ClassList} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"),
                getTagSet("Tutorial_T01")),
            new Student(new Name("Bernice Yu"),
                getTagSet("Tutorial_T01", "Lab_L01")),
            new Student(new Name("Charlotte Oliveiro"),
                getTagSet("Tutorial_T02")),
            new Student(new Name("David Li"),
                getTagSet("Lab_L02")),
            new Student(new Name("Irfan Ibrahim"),
                getTagSet("Recitation_R03")),
            new Student(new Name("Roy Balakrishnan"),
                getTagSet("Lab_L12"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        ClassList sampleAb = new ClassList("T01");
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        UniqueClassLists classLists = new UniqueClassLists();
        classLists.add(sampleAb);
        Tutor tutor = new Tutor(new Name("James"), new HashSet<>(), classLists);
        return tutor;
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
