package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkAppointmentCommand object
 */
public class MarkAppointmentCommandParser implements Parser<MarkAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAppointmentCommand
     * and returns an MarkAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    // notation: markApp {index}
    public MarkAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new MarkAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
