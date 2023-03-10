package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISOEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddIsolatedEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.IsolatedEvent;

/**
 * Parser class for Isolated Event
 */
public class IsolatedEventCommandParser implements Parser<AddIsolatedEventCommand> {

    /**
     * Parse the IsolatedEvent command.
     * @param args
     * @return AddIsolatedEventCommand
     * @throws ParseException
     */
    public AddIsolatedEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ISOEVENT, PREFIX_STARTDATE, PREFIX_ENDDATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddIsolatedEventCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ISOEVENT, PREFIX_STARTDATE, PREFIX_ENDDATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddIsolatedEventCommand.MESSAGE_USAGE));
        }

        String eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_ISOEVENT).get());
        LocalDateTime startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());
        LocalDateTime endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDDATE).get());

        IsolatedEvent eventToAdd = new IsolatedEvent(eventName, startDate, endDate);
        return new AddIsolatedEventCommand(index, eventToAdd);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
