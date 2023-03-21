package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.StartTimeWithinDaysPredicate;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {
    private final LocalDateTime timeNow;

    public RemindCommandParser(LocalDateTime timeNow) {
        this.timeNow = timeNow;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        }

        Integer days;

        try {
            days = ParserUtil.parseDaysNumber(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE), pe);
        }


        return new RemindCommand(new StartTimeWithinDaysPredicate(
                LocalDateTime.now(), days));
    }
}
