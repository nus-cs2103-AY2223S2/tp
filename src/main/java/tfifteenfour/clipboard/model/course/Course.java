package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.AppUtil.checkArgument;

import javafx.collections.ObservableList;

/**
 * Represents a Course in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCourseCode(String)} (String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS = "Course codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String courseCode;
    private UniqueGroupsList groups;

    {
        groups = new UniqueGroupsList();
    }

    /**
     * Constructs a {@code courseCode}.
     *
     * @param courseCode A valid module code.
     */
    public Course(String courseCode) {
        requireNonNull(courseCode);
        checkArgument(isValidCourseCode(courseCode), MESSAGE_CONSTRAINTS);
        this.courseCode = courseCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidCourseCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public ObservableList<Group> getUnmodifiableGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    public ObservableList<Group> getModifiableGrouplist() {
        return groups.asModifiableObservableList();
    }

    /**
     * Returns course code
     * @return string course code
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && courseCode.equals(((Course) other).courseCode)); // state check
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        return courseCode;
    }

    /**
     * Checks if course has specified group.
     * @return true if group has specified group.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds group.
     */
    public void addGroup(Group group) {
        groups.add(group);
    }


    /**
     * Returns true if both courses are the same.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseCode().equals(getCourseCode());
    }
}
