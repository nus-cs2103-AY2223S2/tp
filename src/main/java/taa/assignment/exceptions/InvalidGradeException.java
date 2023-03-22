package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

/**
 * Grade has marks < 0 or > totalMarks.
 */
public class InvalidGradeException extends CommandException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
