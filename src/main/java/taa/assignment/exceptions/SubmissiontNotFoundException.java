package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

/**
 * Submission is not found.
 */
public class SubmissiontNotFoundException extends CommandException {
    public SubmissiontNotFoundException(String message) {
        super(message);
    }
}
