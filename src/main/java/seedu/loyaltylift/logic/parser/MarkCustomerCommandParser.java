package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.MarkCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCustomerCommand object
 */
public class MarkCustomerCommandParser implements Parser<MarkCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCustomerCommand
     * and returns a MarkCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCustomerCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkCustomerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCustomerCommand.MESSAGE_USAGE), pe);
        }
    }
}
