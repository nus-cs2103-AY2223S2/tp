package fasttrack.logic.parser.delete;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.delete.DeleteCategoryCommand;
import fasttrack.logic.parser.Parser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCategoryCommandParser implements Parser<DeleteCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCategoryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCategoryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCategoryCommand.MESSAGE_USAGE), pe);
        }
    }

}
