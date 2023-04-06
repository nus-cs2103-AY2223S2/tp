package seedu.dengue.model.person.exceptions;

/**
 * Signals that the person contains Null attributes.
 */
public class PersonHasNullAttributesException extends RuntimeException {
    public PersonHasNullAttributesException() {
        super("Person contains Null attributes");
    }
}
