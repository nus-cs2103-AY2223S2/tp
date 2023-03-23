package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.MarkAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MarkAppointmentCommandParser implements Parser<MarkAppointmentCommand> {

    // notation: markApp {index}
    public MarkAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new MarkAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
