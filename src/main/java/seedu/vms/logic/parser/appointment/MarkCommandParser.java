package seedu.vms.logic.parser.appointment;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.appointment.MarkCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public MarkCommand parse(ArgumentMultimap argsMap) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(argsMap.getPreamble());
            return new MarkCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
