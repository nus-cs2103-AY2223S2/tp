package seedu.wife.logic.parser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.wife.logic.commands.ListByTagCommand;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;

/**
 * Parses input arguments and creates a new ListByTagCommand object
 */
public class ListByTagCommandParser implements Parser<ListByTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListByTagCommand
     * and returns a ListByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListByTagCommand parse(String args) throws ParseException {
        try {
            Tag tag = ParserUtil.parseTag(args);
            return new ListByTagCommand(tag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListByTagCommand.MESSAGE_USAGE), pe);
        }
    }

}
