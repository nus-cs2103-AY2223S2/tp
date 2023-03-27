package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.recipe.logic.commands.FindIngredientCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.IngredientContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindIngredientCommand object
 */
public class FindIngredientCommandParser implements Parser<FindIngredientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindIngredientCommand
     * and returns a FindIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindIngredientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIngredientCommand.MESSAGE_USAGE));
        }

        String[] recipeKeywords = trimmedArgs.split("\\s+");

        return new FindIngredientCommand(new IngredientContainsKeywordsPredicate(Arrays.asList(recipeKeywords)));
    }

}
