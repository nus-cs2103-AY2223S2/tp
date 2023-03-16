package seedu.address.model.timetable.exceptions;

public class LessonClashException extends RuntimeException {
    public LessonClashException(String errorMessage) {
        super(errorMessage);
    }
}
