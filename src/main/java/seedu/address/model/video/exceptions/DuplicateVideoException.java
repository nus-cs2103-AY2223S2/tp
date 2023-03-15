package seedu.address.model.video.exceptions;

import seedu.address.model.exceptions.DuplicateDataException;

/**
 * Signals that the operation will result in duplicate Videos (Videos are considered duplicates if they have the same
 * name).
 */
public class DuplicateVideoException extends DuplicateDataException {
    public DuplicateVideoException() {
        super("videos");
    }
}
