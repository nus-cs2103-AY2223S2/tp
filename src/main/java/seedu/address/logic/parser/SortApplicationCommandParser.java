package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortApplicationCommand object
 */
public class SortApplicationCommandParser implements ApplicationParser<SortApplicationCommand> {

    private static final String SORT_BY_ALPHABETICAL_KEYWORD = "alphabetical";

    private static final String SORT_BY_DEADLINE_KEYWORD = "deadline";

    /**
     * Parses the given {@code String} of arguments in the context of the SortApplicationCommand
     * and returns a SortApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortApplicationCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals(SORT_BY_ALPHABETICAL_KEYWORD)) {
            return new SortApplicationCommand(SORT_BY_ALPHABETICAL_KEYWORD);
        } else if (trimmedArgs.equals(SORT_BY_DEADLINE_KEYWORD)) {
            return new SortApplicationCommand(SORT_BY_DEADLINE_KEYWORD);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortApplicationCommand.MESSAGE_USAGE));
        }
    }
}
