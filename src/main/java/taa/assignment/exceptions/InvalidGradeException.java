package taa.assignment.exceptions;

import taa.logic.commands.exceptions.CommandException;

public class InvalidGradeException extends CommandException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
