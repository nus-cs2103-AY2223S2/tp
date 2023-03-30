package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

/**
 * Submission is not found.
 */
public class SubmissionNotFoundException extends AssignmentException {
    public SubmissionNotFoundException(String studentName) {
        super("Submission of " + studentName + " not found");
    }
}
