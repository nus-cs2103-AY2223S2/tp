package trackr.logic.parser.menu;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import trackr.commons.core.index.Index;
import trackr.logic.commands.menu.DeleteMenuItemCommand;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMenuItemCommand object.
 */
public class DeleteMenuItemCommandParser implements Parser<DeleteMenuItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMenuItemCommand
     * and returns a DeleteMenuItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteMenuItemCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteMenuItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMenuItemCommand.MESSAGE_USAGE), pe);
        }
    }

}
