package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePartCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePartCommand object
 */
public class DeletePartCommandParser implements Parser<DeletePartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePartCommand
     * and returns a DeletePartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePartCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePartCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePartCommand.MESSAGE_USAGE), pe);
        }
    }

}
