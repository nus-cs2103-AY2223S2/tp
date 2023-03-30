package seedu.address.model.entity.exceptions;

/**
 * Signals that the operation will result in duplicate templates. Templates are defined to be duplicates if they
 * share the same name
 */
public class DuplicateTemplateException extends RuntimeException {
    public DuplicateTemplateException() {
        super("Operation would result in duplicate templates");
    }
}

