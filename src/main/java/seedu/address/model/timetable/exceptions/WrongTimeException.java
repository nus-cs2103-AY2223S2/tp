package seedu.address.model.timetable.exceptions;

public class WrongTimeException extends RuntimeException {
    public WrongTimeException(String errorMessage) {
        super(errorMessage);
    }
}
