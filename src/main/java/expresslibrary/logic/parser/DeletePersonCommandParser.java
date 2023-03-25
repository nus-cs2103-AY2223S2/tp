package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.DeletePersonCommand;
import expresslibrary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeletePersonCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePersonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE), pe);
        }
    }

}
