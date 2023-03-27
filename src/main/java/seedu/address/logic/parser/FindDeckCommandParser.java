package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.deckcommands.FindDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindDeckCommand object
 */
public class FindDeckCommandParser implements Parser<FindDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindDeckCommand
     * and returns a FindDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDeckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeckCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDeckCommand(Arrays.asList(nameKeywords));
    }

}
