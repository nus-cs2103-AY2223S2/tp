package seedu.socket.logic.parser;


import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.model.person.Person.CATEGORIES;
import static seedu.socket.model.person.Person.CATEGORY_NAME;

import seedu.socket.logic.commands.SortCommand;
import seedu.socket.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new SortCommand(CATEGORY_NAME);
        } else if (CATEGORIES.contains(trimmedArgs)) {
            return new SortCommand(trimmedArgs);
        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
