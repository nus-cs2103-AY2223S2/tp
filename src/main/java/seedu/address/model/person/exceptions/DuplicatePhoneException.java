package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate phone usage (Phones are considered duplicates if they are used by
 * two different Parents).
 */
public class DuplicatePhoneException extends RuntimeException {
    /**
     * A constructor that creates a new DuplicatePhoneException when a Phone is already being used by another Parent
     */
    public DuplicatePhoneException() {
        super("Unable to perform the following operation as you are trying to use "
                + "a phone number that is already used by existing parent to create a new parent!");
    }
}
