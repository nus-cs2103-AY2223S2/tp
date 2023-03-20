package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

@FunctionalInterface
public interface CommandSupplier {
    public Command parse(String args) throws ParseException;
}
