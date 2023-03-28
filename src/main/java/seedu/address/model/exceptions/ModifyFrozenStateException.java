package seedu.address.model.exceptions;

/**
 * Represents a failure to freeze or unfreeze a Model, due to it already being frozen or unfrozen.
 */
public class ModifyFrozenStateException extends Exception {

    public ModifyFrozenStateException(String message) {
        super(message);
    }
}
