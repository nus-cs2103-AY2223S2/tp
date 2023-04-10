package seedu.address.model.lecture.exceptions;

import seedu.address.model.exceptions.DuplicateDataException;

/**
 * Signals that the operation will result in duplicate Lectures (Lectures are considered duplicates if they have the
 * same name).
 */
public class DuplicateLectureException extends DuplicateDataException {
    public DuplicateLectureException() {
        super("lectures");
    }
}
