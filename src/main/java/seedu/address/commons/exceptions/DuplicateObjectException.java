package seedu.address.commons.exceptions;

/**
 * Signals that the operation will result in duplicate Objects.
 */
public class DuplicateObjectException extends RuntimeException {

    /**
     * Constructs a new duplicate person exception.
     *
     * @param pluralNoun Plural form of the object that is duplicated.
     */
    public DuplicateObjectException(String pluralNoun) {
        super("Operation would result in duplicate " + pluralNoun);
    }
}
