package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteReminder;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteReminder object
 */
public class DeleteReminderParser implements Parser<DeleteReminder> {
    @Override
    public DeleteReminder parse(String args) throws ParseException {
        try {
            return new DeleteReminder(Integer.parseInt(args.trim()));
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReminder.MESSAGE_USAGE), e);
        }
    }
}
