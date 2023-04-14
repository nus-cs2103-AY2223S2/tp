package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.powercards.logic.commands.deckcommands.FindDecksCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindDecksCommand object
 */
public class FindDecksCommandParser implements Parser<FindDecksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindDecksCommand
     * and returns a FindDecksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDecksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDecksCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDecksCommand(Arrays.asList(nameKeywords));
    }

}
