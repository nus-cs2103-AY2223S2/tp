package seedu.internship.logic.parser.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_END;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_START;

import seedu.internship.logic.commands.event.EventFindCommand;
import seedu.internship.logic.commands.event.EventFindCommand.FilterEventDescriptor;
import seedu.internship.logic.parser.ArgumentMultimap;
import seedu.internship.logic.parser.ArgumentTokenizer;
import seedu.internship.logic.parser.Parser;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EventFindCommand object.
 */
public class EventFindCommandParser implements Parser<EventFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventFindCommand
     * and returns an EventFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EventFindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_EVENT_NAME, PREFIX_EVENT_START, PREFIX_EVENT_END);


        FilterEventDescriptor filterEventDescriptor = new FilterEventDescriptor();
        if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            filterEventDescriptor.setName(
                    EventParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_START).isPresent()) {
            filterEventDescriptor.setStart(
                    EventParserUtil.parseEventStart(argMultimap.getValue(PREFIX_EVENT_START).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_END).isPresent()) {
            filterEventDescriptor.setEnd(
                    EventParserUtil.parseEventEnd(argMultimap.getValue(PREFIX_EVENT_END).get()));
        }
        if (!filterEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE));
        }
        return new EventFindCommand(filterEventDescriptor);
    }
}
