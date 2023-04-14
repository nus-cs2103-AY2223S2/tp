package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.powercards.logic.commands.cardcommands.FindCardsCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCardsCommand object
 */
public class FindCardsCommandParser implements Parser<FindCardsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCardsCommand
     * and returns a FindCardsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCardsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCardsCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCardsCommand(Arrays.asList(nameKeywords));
    }

}
