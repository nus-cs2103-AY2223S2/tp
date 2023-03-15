package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYOFWEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddRecurringEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.RecurringEvent;

/**
 * Parser class for Recurring Event
 */
public class AddRecurringEventCommandParser implements Parser<AddRecurringEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecurringEventCommand
     * @param args the String input keyed by the user.
     * @return AddRecurringEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecurringEventCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECURRINGEVENT, PREFIX_DAYOFWEEK,
                        PREFIX_STARTDATETIME, PREFIX_ENDDATETIME);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurringEventCommand.MESSAGE_USAGE), ive);
        }


        if (!arePrefixesPresent(argMultimap, PREFIX_RECURRINGEVENT, PREFIX_DAYOFWEEK, PREFIX_STARTDATETIME,
                PREFIX_ENDDATETIME) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurringEventCommand.MESSAGE_USAGE));
        }

        String eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_RECURRINGEVENT).get());
        DayOfWeek day = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAYOFWEEK).get());
        LocalTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_STARTDATETIME).get());
        LocalTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_ENDDATETIME).get());

        ParserUtil.parsePeriod(startTime, endTime);

        RecurringEvent eventToAdd = new RecurringEvent(eventName, day, startTime, endTime);

        return new AddRecurringEventCommand(index, eventToAdd);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
