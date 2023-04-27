package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.ViewCommand;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE
                ),
                pe
            );
        }
    }
}
