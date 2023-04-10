package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import expresslibrary.logic.commands.FindBookCommand;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.book.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBookCommand object
 */
public class FindBookCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBookCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBookCommand(new TitleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
