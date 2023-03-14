package taa.commons.util;

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
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        ClassList sampleAb = new ClassList();
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
