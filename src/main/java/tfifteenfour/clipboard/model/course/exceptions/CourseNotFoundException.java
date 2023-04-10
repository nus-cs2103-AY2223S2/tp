package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation is unable to find the specified course.
 */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found.");
    }
}
