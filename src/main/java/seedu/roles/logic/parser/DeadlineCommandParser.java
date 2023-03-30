package seedu.roles.logic.parser;

import static seedu.roles.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.roles.logic.commands.DeadlineCommand;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.model.job.Order;

/**
 * Parses input arguments and creates a new SalaryCommand object
 */
public class DeadlineCommandParser implements Parser<DeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the Deadlineommand
     * and returns a DeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeadlineCommand parse(String args) throws ParseException {
        try {
            Order order = ParserUtil.parseOrder(args);
            return new DeadlineCommand(order);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE), pe);
        }
    }
}
