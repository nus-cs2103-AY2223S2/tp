package seedu.address.model.exceptions;

/**
 * Signals that the operation will result in duplicate data.
 */
public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException(String dataName) {
        super(String.format("Operation would result in duplicate %s", dataName));
    }
}
