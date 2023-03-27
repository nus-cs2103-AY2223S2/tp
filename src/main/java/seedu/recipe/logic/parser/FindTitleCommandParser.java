package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.recipe.logic.commands.FindTitleCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTitleCommand object
 */
public class FindTitleCommandParser implements Parser<FindTitleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTitleCommand
     * and returns a FindTitleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTitleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTitleCommand.MESSAGE_USAGE));
        }

        String[] recipeKeywords = trimmedArgs.split("\\s+");

        return new FindTitleCommand(new TitleContainsKeywordsPredicate(Arrays.asList(recipeKeywords)));
    }

}
