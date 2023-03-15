package codoc.model.person;

import static codoc.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's Course in CoDoc.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course should only contain alphanumerical characters, and it should not be blank";

    /*
     * The first character of the Course must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z ]*";

    public final String course;

    /**
     * Constructs a {@code course}.
     *
     * @param course A valid course.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        this.course = course;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidCourse(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return course;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && course.equals(((Course) other).course)); // state check
    }

    @Override
    public int hashCode() {
        return course.hashCode();
    }

}
