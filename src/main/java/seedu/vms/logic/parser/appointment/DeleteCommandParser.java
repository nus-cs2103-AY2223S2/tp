package seedu.vms.logic.parser.appointment;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.appointment.DeleteCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteCommand parse(ArgumentMultimap argsMap) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(argsMap.getPreamble());
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
