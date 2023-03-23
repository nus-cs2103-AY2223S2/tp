package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a method that takes in a string to parse for arguments,
 * and returns a {@link Command}
 */
@FunctionalInterface
public interface CommandSupplier {
    public Command parse(String args) throws ParseException;
}
