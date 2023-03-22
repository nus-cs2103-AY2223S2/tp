package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

public class SubmissiontNotFoundException extends CommandException {
    public SubmissiontNotFoundException(String message) {
        super(message);
    }
}
