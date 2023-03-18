package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListEvContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListEvContactCommand object
 */
public class ListEvContactCommandParser implements Parser<ListEvContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListEvContactCommand
     * and returns a ListEvContact object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListEvContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListEvContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEvContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
