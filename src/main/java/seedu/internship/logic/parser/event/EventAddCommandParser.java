package seedu.internship.logic.parser.event;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_END;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_START;

import java.util.stream.Stream;

import seedu.internship.logic.commands.event.EventAddCommand;
import seedu.internship.logic.parser.ArgumentMultimap;
import seedu.internship.logic.parser.ArgumentTokenizer;
import seedu.internship.logic.parser.Parser;
import seedu.internship.logic.parser.Prefix;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventDescription;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;

/**
 * Parses input arguments and creates a new EventAddCommand object.
 */
public class EventAddCommandParser implements Parser<EventAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventAddCommand
     * and returns an EventAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EventAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME, PREFIX_EVENT_START, PREFIX_EVENT_END,
                        PREFIX_EVENT_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_EVENT_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventAddCommand.MESSAGE_USAGE));
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_START)) {
            // If Start is not present , then start == end
            argMultimap.put(PREFIX_EVENT_START, argMultimap.getValue(PREFIX_EVENT_END).get());
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_DESCRIPTION)) {
            // If Description not present, then empty description
            argMultimap.put(PREFIX_EVENT_DESCRIPTION, "");
        }

        Name name = EventParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get());

        Start start = EventParserUtil.parseEventStart(argMultimap.getValue(PREFIX_EVENT_START).get());

        End end = EventParserUtil.parseEventEnd(argMultimap.getValue(PREFIX_EVENT_END).get());

        EventDescription desc = EventParserUtil.parseEventDescription(
                argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).get());

        Event event = new Event(name, start, end, desc);

        return new EventAddCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
