package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.ViewOrderCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewOrderCommand object
 */
public class ViewOrderCommandParser implements Parser<ViewOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewOrderCommand
     * and returns a ViewOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
