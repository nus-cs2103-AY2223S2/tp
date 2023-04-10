package tfifteenfour.clipboard.model.course.exceptions;

/**
 * Signals that the operation is unable to find the specified Group.
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Can't find such group in this course.");
    }
}
