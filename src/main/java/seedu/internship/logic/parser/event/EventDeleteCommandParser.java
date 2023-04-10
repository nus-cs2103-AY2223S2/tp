package seedu.internship.logic.parser.event;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.event.EventDeleteCommand;
import seedu.internship.logic.parser.Parser;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EventDeleteCommand object.
 */
public class EventDeleteCommandParser implements Parser<EventDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventDeleteCommand
     * and returns an EventDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EventDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = EventParserUtil.parseIndex(args);
            return new EventDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
