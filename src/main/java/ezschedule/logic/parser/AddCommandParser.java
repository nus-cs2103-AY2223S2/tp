package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_EVENT_END_TIME_EARLIER_THAN_START_TIME;
import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;

import java.util.stream.Stream;

import ezschedule.logic.commands.AddCommand;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_START, PREFIX_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE, PREFIX_START, PREFIX_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElse(null));
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).orElse(null));
        Time startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START).orElse(null));
        Time endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END).orElse(null));

        if (endTime.isBefore(startTime)) {
            throw new ParseException(MESSAGE_EVENT_END_TIME_EARLIER_THAN_START_TIME);
        }

        Event event = new Event(name, date, startTime, endTime);

        return new AddCommand(event);
    }
}
