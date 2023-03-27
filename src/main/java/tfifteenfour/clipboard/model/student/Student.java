package tfifteenfour.clipboard.model.student;

import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Remark remark;
    private final StudentId studentId;
    // private final Set<Tag> tags = new HashSet<>();
    // private final Set<Course> modules = new HashSet<>();

    /**
     * With remark and tags field.
     */
    public Student(Name name, Phone phone, Email email, StudentId studentId, Remark remark) {
        requireAllNonNull(name, phone, email, studentId, remark);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.studentId = studentId;
        // this.modules.addAll(modules);
        this.remark = remark;
        // this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Remark getRemark() {
        return remark;
    }

    /*
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    // public Set<Tag> getTags() {
    //     return Collections.unmodifiableSet(tags);
    // }

    /*
     * Returns an immutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    // public Set<Course> getModules() {
    //     return Collections.unmodifiableSet(modules);
    // }


    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
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
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getStudentId().equals(getStudentId());
        // && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, studentId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; StudentId: ")
                .append(getStudentId());

        // Set<Course> modules = getModules();
        // builder.append("; Modules: ");
        // modules.forEach(builder::append);

        builder.append("; Remark: ")
                .append(getRemark());

        // Set<Tag> tags = getTags();
        // if (!tags.isEmpty()) {
        //     builder.append("; Tags: ");
        //     tags.forEach(builder::append);
        // }
        return builder.toString();
    }
}
