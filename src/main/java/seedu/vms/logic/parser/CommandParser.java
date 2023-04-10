package seedu.vms.logic.parser;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * A parser to parse commands from {@link ArgumentMultimap}.
 */
public interface CommandParser {
    /**
     * Parses the command from the given {@code ArgumentMultimap}.
     *
     * @param args - the {@code ArgumentMultimap} to parse the command from.
     * @throws ParseException - if the command cannot be parsed.
     */
    public Command parse(ArgumentMultimap args) throws ParseException;
}
