package taa.model.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;
import taa.model.assignment.Assignment;

/**
 * Represents an error which occurs during execution involving an {@link Assignment}.
 */
public class AssignmentException extends CommandException {
    public AssignmentException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public AssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
