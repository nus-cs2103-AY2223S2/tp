package seedu.ultron.logic.parser;

import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ultron.logic.commands.UpcomingCommand;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.opening.OpeningsBeforeDaysPredicate;

/**
 * Parses input arguments and creates a new UpcomingCommand object
 */
public class UpcomingCommandParser implements Parser<UpcomingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpcomingCommand
     * and returns an UpcomingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpcomingCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            int days = ParserUtil.parseDays(trimmedArgs);
            return new UpcomingCommand(new OpeningsBeforeDaysPredicate(days));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpcomingCommand.MESSAGE_USAGE), pe);
        }
    }
}
