package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

/**
 * Assignment is not found.
 */
public class AssignmentNotFoundException extends CommandException {
    public AssignmentNotFoundException(String message) {
        super(message);
    }
}
