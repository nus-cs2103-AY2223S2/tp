package trackr.logic.parser.order;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import trackr.commons.core.index.Index;
import trackr.logic.commands.order.DeleteOrderCommand;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;

//@@author chongweiguan-reused
/**
 * Parses input arguments and creates a new DeleteOrderCommand object.
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOrderCommand
     * and returns a DeleteOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
    //@@author
}
