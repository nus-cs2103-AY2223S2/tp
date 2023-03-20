package codoc.model.person;

import static codoc.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Person's Course in CoDoc.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final ArrayList<String> COURSE_LIST = new ArrayList<>(
            Arrays.asList(
                    "Computer Science",
                    "Business Analytics",
                    "Information Systems",
                    "Information Security",
                    "Computer Engineering"
            )
    );

    public static final String MESSAGE_CONSTRAINTS =
            "Course should only contain numerical characters\n"
                    + "corresponding to the index of the course, \n"
                    + "and it should not be blank";

    public final String course;

    /**
     * Constructs a {@code course}.
     *
     * @param index A valid one-based index object.
     */
    public Course(String index) {
        requireNonNull(index);
        checkArgument(isValidCourse(index), MESSAGE_CONSTRAINTS);
        this.course = COURSE_LIST.get(Integer.parseInt(index) - 1);
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidCourse(String test) {
        try {
            int i = Integer.parseInt(test);
            return i <= COURSE_LIST.size() && i > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the one-based index of the given course within the list of courses
     * @param course A valid course
     * @return The index of the course
     */
    public static String getIndex(String course) {
        System.out.println(COURSE_LIST.contains(course));
        if (COURSE_LIST.contains(course)) {
            System.out.println(String.valueOf(COURSE_LIST.indexOf(course) + 1));
            return String.valueOf(COURSE_LIST.indexOf(course) + 1);
        } else {
            return null;
        }
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
