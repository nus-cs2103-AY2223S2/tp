package seedu.techtrack.logic.parser;

import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.techtrack.logic.commands.SalaryCommand;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SalaryCommand object
 */
public class SalaryCommandParser implements Parser<SalaryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SalaryCommand
     * and returns a SalaryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SalaryCommand parse(String args) throws ParseException {
        try {
            OrderParser orderParser = ParserUtil.parseOrder(args);
            return new SalaryCommand(orderParser);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalaryCommand.MESSAGE_USAGE), pe);
        }
    }
}
