package fasttrack.logic.parser.delete;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.delete.DeleteExpenseCommand;
import fasttrack.logic.parser.Parser;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExpenseCommandParser object
 */
public class DeleteExpenseCommandParser implements Parser<DeleteExpenseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExpenseCommandParser
     * and returns a DeleteExpenseCommandParser object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExpenseCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExpenseCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExpenseCommand.MESSAGE_USAGE), pe);
        }
    }

}
