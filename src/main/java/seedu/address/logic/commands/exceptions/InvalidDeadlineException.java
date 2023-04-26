package seedu.address.logic.commands.exceptions;

/**
 * This exception is thrown when a task's deadline is earlier than its creation date.
 *
 */
public class InvalidDeadlineException extends CommandException {
    /**
     * Constructs a new InvalidDeadlineException with the default error message.
     */
    public InvalidDeadlineException() {
        super("The task deadline must come after its creation date.");
    }
}
