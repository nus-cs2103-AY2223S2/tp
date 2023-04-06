package seedu.address.logic.parser;

import seedu.address.logic.commands.SampleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SampleCommand object
 */
public class SampleCommandParser implements Parser<SampleCommand> {
    public static final String MESSAGE_INVALID_SIZE =
            "Invalid size. Please select a number between 1 and 100 inclusive.";

    @Override
    public SampleCommand parse(String args) throws ParseException {
        try {
            int size = ParserUtil.parseInt(args);
            if (size < 1 || size > 100) {
                throw new ParseException(String.format(MESSAGE_INVALID_SIZE));
            }
            return new SampleCommand(size);
        } catch (ParseException pe) {
            throw new ParseException(
                    pe.getMessage(), pe);

        }
    }
}
