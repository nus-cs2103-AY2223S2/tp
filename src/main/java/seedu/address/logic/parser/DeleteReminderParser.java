package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteReminderCommand object
 */
public class DeleteReminderParser implements Parser<DeleteReminderCommand> {
    @Override
    public DeleteReminderCommand parse(String args) throws ParseException {
        try {
            return new DeleteReminderCommand(Integer.parseInt(args.trim()));
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReminderCommand.MESSAGE_USAGE), e);
        }
    }
}
