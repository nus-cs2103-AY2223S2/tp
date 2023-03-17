package seedu.vms.logic.parser;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.parser.exceptions.ParseException;

public interface CommandParser {
    public Command parse(ArgumentMultimap args) throws ParseException;
}
