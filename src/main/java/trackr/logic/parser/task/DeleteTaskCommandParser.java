package trackr.logic.parser.task;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import trackr.commons.core.index.Index;
import trackr.logic.commands.task.DeleteTaskCommand;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object.
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }

}
