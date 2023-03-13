package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.DeleteCustomerCommand;
import seedu.loyaltylift.logic.commands.DeleteOrderCommand;
import seedu.loyaltylift.logic.parser.Parser;
import seedu.loyaltylift.logic.parser.ParserUtil;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCustomerCommand object
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCustomerCommand
     * and returns a DeleteCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
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

}
