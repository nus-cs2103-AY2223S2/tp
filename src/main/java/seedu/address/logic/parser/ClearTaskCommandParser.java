package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ClearTaskCommandParser implements Parser<ClearTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CLearTaskCommand
     * and returns a ClearTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearTaskCommand.MESSAGE_USAGE), pe);
        }
    }

}
