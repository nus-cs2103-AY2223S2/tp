package seedu.ultron.logic.parser;

import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ultron.logic.commands.UpcomingCommand;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.opening.OpeningsBeforeDaysPredicate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpcomingCommandParser implements Parser<UpcomingCommand> {
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
