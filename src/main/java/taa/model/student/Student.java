package taa.model.student;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import taa.assignment.Submission;
import taa.assignment.exceptions.AssignmentNotFoundException;
import taa.commons.util.CollectionUtil;
import taa.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private static int lastId;
    // Identity fields
    private final Name name;
    private final int id;

    // Data fields
    private final Attendance atd;
    private final Set<Tag> classTags = new HashSet<>();
    private final Submissions submissions;

    private final int hashcode;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, String atd, String pp, Set<Tag> classTags) {
        CollectionUtil.requireAllNonNull(name, classTags);
        this.id = ++lastId;
        this.name = name;
        this.atd = new Attendance(atd, pp);
        this.classTags.addAll(classTags);
        this.submissions = new Submissions();
        hashcode = Objects.hash(name, this.classTags);
    }

    public int getId() {
        return this.id;
    }
    public Name getName() {
        return name;
    }

    /**
     * @return number of week student attended class
     */
    public int getNumWeeksPresent() {
        return this.atd.getNumWeeksPresent();
    }

    /**
     * @return the average participation points of student in all classes attended so far
     */
    public float getPartPoints() {
        return this.atd.getAveragePP();
    }

    public Attendance getAtd() {
        return this.atd;
    }

    /**
     * Returns an immutable class tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getClassTags() {
        return Collections.unmodifiableSet(classTags);
    }

    /**
     * Attributes a new submission to this student.
     */
    public void addSubmission(Submission submission) {
        this.submissions.addSubmission(submission);
    }

    /**
     * Removes a submission attributed to this student.
     */
    public void deleteSubmission(Submission submission) {
        this.submissions.deleteSubmission(submission);
    }

    /**
     * Returns the latest submission by this student.
     */
    public Submission getLatestSubmission() {
        return this.submissions.getLatestSubmission();
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
     * Returns the grade obtained by the student, if it exists.
     * Otherwise, an Optional.empty() is returned.
     * @throws AssignmentNotFoundException if the assignment does not exist.
     */
    public Optional<Integer> getGradesForAssignment(String assignmentName)
            throws AssignmentNotFoundException {
        return this.submissions.getSubmissionScore(assignmentName);
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
                && otherStudent.getClassTags().equals(getClassTags());
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Tag> classes = getClassTags();
        if (!classes.isEmpty()) {
            builder.append("; Classes: ");
            classes.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Updates the attendance counter for the entire class list.
     */
    public void updateAttendanceCounter(int[] counter) {
        this.atd.updateAttendanceCounter(counter);
    }

}
