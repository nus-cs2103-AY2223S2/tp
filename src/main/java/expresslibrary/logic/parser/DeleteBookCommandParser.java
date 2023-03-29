package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.DeleteBookCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBookCommand object
 */
public class DeleteBookCommandParser implements Parser<DeleteBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteBookCommand
     * and returns a DeleteBookCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBookCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookCommand.MESSAGE_USAGE), pe);
        }
    }

}
