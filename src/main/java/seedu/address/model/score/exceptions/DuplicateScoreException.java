package seedu.address.model.score.exceptions;

//@@author astraxq
/**
 * Signals that the operation will result in duplicate Scores (Scores are considered duplicates if they have the same
 * identity).
 */
public class DuplicateScoreException extends RuntimeException {
    public DuplicateScoreException() {
        super("Operation would result in duplicate scores");
    }
}
//@author astraxq
