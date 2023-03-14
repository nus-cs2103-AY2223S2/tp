package taa.model.student;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import taa.commons.util.CollectionUtil;
import taa.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private static int lastId = 0;
    // Identity fields
    private final Name name;
    private final int id;

    private final Attendance atd;

    // Data fields
    private final Set<Tag> classes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Tag initialClass) {
        CollectionUtil.requireAllNonNull(name, initialClass);
        this.id = ++lastId;
        this.name = name;
        this.atd = new Attendance();
        this.classes.add(initialClass);
    }

    public int getId() {
        return this.id;
    }
    public Name getName() {
        return name;
    }

    public int getNumWeeksPresent() {
        return this.atd.getNumWeeksPresent();
    }

    public Attendance getAtd() {
        return this.atd;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getClasses() {
        return Collections.unmodifiableSet(classes);
    }

    /**
     * Associates this student with a new class/class list.
     * @param newClass The new class/class list to associate this student with.
     */
    public void addClass(Tag newClass) {
        this.classes.add(newClass);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
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
                && otherStudent.getClass().equals(getClasses());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, classes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Tag> classes = getClasses();
        if (!classes.isEmpty()) {
            builder.append("; Classes: ");
            classes.forEach(builder::append);
        }
        return builder.toString();
    }

}
