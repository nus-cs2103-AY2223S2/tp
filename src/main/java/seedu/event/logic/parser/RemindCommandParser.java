package seedu.event.logic.parser;

import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.Clock;

import seedu.event.logic.commands.RemindCommand;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.event.StartTimeWithinDaysPredicate;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {
    private final Clock clock;

    public RemindCommandParser(Clock clock) {
        this.clock = clock;
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
                clock, days));
    }
}
