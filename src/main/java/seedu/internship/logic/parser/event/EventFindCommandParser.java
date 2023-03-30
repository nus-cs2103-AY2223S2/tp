package seedu.internship.logic.parser.event;

import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.commands.event.EventFindCommand;
import seedu.internship.logic.commands.event.EventFindCommand.FilterEventDescriptor;
import seedu.internship.logic.parser.ArgumentMultimap;
import seedu.internship.logic.parser.ArgumentTokenizer;
import seedu.internship.logic.parser.Parser;
import seedu.internship.logic.parser.ParserUtil;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.logic.parser.event.EventParserUtil.*;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.*;;

public class EventFindCommandParser implements Parser<EventFindCommand> {

    public EventFindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_EVENT_NAME, PREFIX_EVENT_START, PREFIX_EVENT_END);


        FilterEventDescriptor filterEventDescriptor = new FilterEventDescriptor();
        if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
            filterEventDescriptor.setName(EventParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_START).isPresent()) {
            filterEventDescriptor.setStart(EventParserUtil.parseEventStart(argMultimap.getValue(PREFIX_EVENT_START).get()));
        }
        if (argMultimap.getValue(PREFIX_EVENT_END).isPresent()) {
            filterEventDescriptor.setEnd(EventParserUtil.parseEventEnd(argMultimap.getValue(PREFIX_EVENT_END).get()));
        }
        if (!filterEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FindCommand.MESSAGE_NOT_FILTERED);
        }
        return new EventFindCommand(filterEventDescriptor);
    }
}
