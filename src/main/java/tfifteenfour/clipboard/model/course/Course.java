package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

import tfifteenfour.clipboard.model.student.Group;

/**
 * Represents a Module in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleCode(String)} (String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String courseCode;
	private ArrayList<Group> groups = new ArrayList<Group>();

    /**
     * Constructs a {@code courseCode}.
     *
     * @param courseCode A valid module code.
     */
    public Course(String courseCode) {
        requireNonNull(courseCode);
        checkArgument(isValidModuleCode(courseCode), MESSAGE_CONSTRAINTS);
        this.courseCode = courseCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
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
     * Format state as text for viewing.
     */
    public String toString() {
        return '{' + courseCode + '}';
    }

    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseCode().equals(getCourseCode());
    }
}
