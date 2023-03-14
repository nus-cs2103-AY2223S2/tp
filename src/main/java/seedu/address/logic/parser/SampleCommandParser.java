package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SampleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SampleCommand object
 */
public class SampleCommandParser implements Parser<SampleCommand> {
    @Override
    public SampleCommand parse(String args) throws ParseException {
        try {
            int size = ParserUtil.parseInt(args);
            if (size < 0 || size > 100) {
                throw new ParseException("Invalid size. Please select a number between 1 and 100 inclusive.");
            }
            return new SampleCommand(size);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SampleCommand.MESSAGE_USAGE), pe);
        }
    }
}
