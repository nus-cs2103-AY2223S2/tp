package taa.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import taa.commons.util.CollectionUtil;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.student.UniqueStudentList;
import taa.model.tag.Tag;

/**
 * Represents a tutor in the TAA application
 */
public class Tutor implements ReadOnlyAddressBook {
    private static int lastId = 0;
    // Identity fields
    private final Name name;
    private final int id;
    private final UniqueClassLists classLists;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Set<Tag> tags, UniqueClassLists classes) {
        CollectionUtil.requireAllNonNull(name, tags);
        this.id = ++lastId;
        this.name = name;
        this.tags.addAll(tags);
        this.classLists = classes;
    }

    public int getId() {
        return this.id;
    }
    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getClassTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append("; Email: ");

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    private UniqueStudentList getAllStudents() {
        UniqueStudentList students = new UniqueStudentList();
        for (ClassList classList: this.classLists) {
            students.appendList(classList.getUniqueStudentList());
        }
        return students;
    }

    public boolean containsClassList(ClassList tocheck) {
        return this.classLists.contains(tocheck);
    }

    public void addClass(ClassList toAdd) {
        this.classLists.add(toAdd);
    }

    public ObservableList<Student> getStudentList() {
        return this.getAllStudents().asUnmodifiableObservableList();
    }

    public ObservableList<ClassList> getClassList() {
        return classLists.asUnmodifiableObservableList();
    }

    public void addStudentToClass(Student student, String className) {
        this.classLists.addStudent(student, className);
    }

}
