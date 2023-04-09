package trackr.logic.parser.menu;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import trackr.logic.commands.menu.FindMenuItemCommand;
import trackr.logic.parser.Parser;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.menu.ItemNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMenuItemCommand object.
 */
public class FindMenuItemCommandParser implements Parser<FindMenuItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMenuItemCommand
     * and returns a FindMenuItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindMenuItemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMenuItemCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMenuItemCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
