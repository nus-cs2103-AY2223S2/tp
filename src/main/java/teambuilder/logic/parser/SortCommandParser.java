package teambuilder.logic.parser;

import teambuilder.logic.commands.SortCommand;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] argsArr = trimmedArgs.split("\\s+");
        String order = argsArr[0].toLowerCase();
        String sortBy = argsArr[1].toLowerCase();

        return new SortCommand(order, sortBy);
    }

}
