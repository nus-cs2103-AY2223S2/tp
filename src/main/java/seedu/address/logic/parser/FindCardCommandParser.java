package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.cardcommands.FindCardCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCardCommand object
 */
public class FindCardCommandParser implements Parser<FindCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCardCommand
     * and returns a FindCardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCardCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCardCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCardCommand(Arrays.asList(nameKeywords));
    }

}
