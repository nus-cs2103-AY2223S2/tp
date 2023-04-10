package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.UnmarkCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkCustomerCommand object
 */
public class UnmarkCustomerCommandParser implements Parser<UnmarkCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCustomerCommand
     * and returns a UnmarkCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkCustomerCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkCustomerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCustomerCommand.MESSAGE_USAGE), pe);
        }
    }
}
