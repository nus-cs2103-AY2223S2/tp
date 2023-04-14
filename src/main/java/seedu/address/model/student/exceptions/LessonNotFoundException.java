package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in lessons with conflicting timeslots
 */
public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
        super("Sorry, the lesson does not exist");
    }
}
