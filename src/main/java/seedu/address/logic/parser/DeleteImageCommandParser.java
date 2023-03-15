package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteImageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteImageCommand object
 */
public class DeleteImageCommandParser implements Parser<DeleteImageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of
     * the DeleteImageCommand and returns a DeleteImageCommand object
     * for execution.
     *
     * @param args The string of arguments
     * @return The DeleteImageCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteImageCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteImageCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
