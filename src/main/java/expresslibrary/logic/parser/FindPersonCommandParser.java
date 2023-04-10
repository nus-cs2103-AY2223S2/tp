package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import expresslibrary.logic.commands.FindPersonCommand;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
