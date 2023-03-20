package arb.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate TagMappings (TagMappings are considered
 * duplicates if they have the same name).
 */
public class DuplicateTagMappingException extends RuntimeException {
    public DuplicateTagMappingException() {
        super("Operation would result in duplicate tags");
    }
}
